package app.proxma.mytelenor;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Answer key for Answer & Win / Test Your Skills, logged-in or guest.
 *
 * <p>Called from the app's own quiz Gson models (see ShowQuizAnswersPatch): the response data class
 * ({@link #onQuestions}), each question ({@link #onQuestion}) and each choice ({@link #onChoice}). The
 * choice objects carry {@code isCorrect}; this class prefixes the 1-based option number (1..4) to the
 * text of the correct ones, builds the compact numeric answer key (e.g. {@code 21323}), and posts a
 * notification + Toast listing the key and every correct answer. Only fields are read here (never
 * getters), so the hooked getters cannot recurse.
 */
public final class QuizHelper {

    private static final String TAG = "PROXMA_MT";
    private static final long SESSION_MS = 2 * 60 * 60 * 1000L;

    /** JSON keys that hold the visible text of a choice, most likely first. */
    private static final String[] TEXT_KEYS = {
        "choice", "choiceText", "choice_text", "text", "answer", "answerText", "option", "optionText",
        "title", "name", "choiceName", "label", "value", "description",
    };
    private static final String[] CORRECT_KEYS = {"isCorrect", "is_correct", "correct", "isRight", "isAnswer"};

    private static final Object NONE = new Object();
    private static final Map<Class<?>, Object> sCorrectField = new HashMap<>();
    private static final Map<Class<?>, Object> sTextField = new HashMap<>();

    private static final class AnswerItem {
        final int optionNum;
        final String text;

        AnswerItem(int optionNum, String text) {
            this.optionNum = optionNum;
            this.text = text;
        }
    }

    private static final Object LOCK = new Object();
    private static final LinkedHashMap<Integer, AnswerItem> sQuestionsMap = new LinkedHashMap<>();
    private static final Map<Integer, Integer> sChoiceOptionNum = new HashMap<>();
    private static final Map<Integer, Integer> sChoiceToQuestion = new HashMap<>();
    private static final Map<Integer, String> sCleanText = new HashMap<>();
    private static final LinkedHashSet<Integer> sSeenChoices = new LinkedHashSet<>();

    private static long sSessionStart = 0L;
    private static String sLastNotified = "";
    private static volatile Context sContext;
    private static volatile boolean sStarted = false;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static final Runnable FLUSH = QuizHelper::flush;

    private QuizHelper() {}

    // ---------------------------------------------------------------------------------------------
    // Entry points (injected by the patch)
    // ---------------------------------------------------------------------------------------------

    public static void onAppStart(final Activity activity) {
        try {
            if (activity == null) return;
            sContext = activity.getApplicationContext();
            if (sStarted) return;
            sStarted = true;
            Log.i(TAG, "QUIZ_HELPER active");
            MAIN.postDelayed(() -> {
                try {
                    Toast.makeText(sContext, "Proxma: quiz answers helper active", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= 33
                        && activity.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0
                        && !activity.isFinishing()) {
                        activity.requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 0x5051);
                    }
                } catch (Throwable t) {
                    Log.w(TAG, "QUIZ_HELPER start ui " + t);
                }
            }, 2500L);
        } catch (Throwable t) {
            Log.w(TAG, "QUIZ_HELPER onAppStart " + t);
        }
    }

    /** The response data object (holds the question list). Replaces the current answer set. */
    public static void onQuestions(Object data) {
        try {
            if (data == null) return;
            List<Object> questions = collectionElements(data);
            if (questions.isEmpty()) return;
            boolean anyFound = false;
            synchronized (LOCK) {
                LinkedHashMap<Integer, AnswerItem> fresh = new LinkedHashMap<>();
                for (Object question : questions) {
                    if (question == null) continue;
                    AnswerItem item = processQuestionLocked(question);
                    if (item != null) {
                        fresh.put(System.identityHashCode(question), item);
                        anyFound = true;
                    }
                }
                if (anyFound) {
                    sQuestionsMap.clear();
                    sQuestionsMap.putAll(fresh);
                    sSessionStart = System.currentTimeMillis();
                }
            }
            if (anyFound) scheduleFlush(0L);
        } catch (Throwable t) {
            Log.w(TAG, "QUIZ_HELPER onQuestions " + t);
        }
    }

    /** A single question object (holds its choice list). */
    public static void onQuestion(Object question) {
        try {
            if (question == null) return;
            boolean changed = false;
            synchronized (LOCK) {
                checkSessionExpiryLocked();
                AnswerItem item = processQuestionLocked(question);
                if (item != null) {
                    int qId = System.identityHashCode(question);
                    AnswerItem prev = sQuestionsMap.put(qId, item);
                    if (prev == null || prev.optionNum != item.optionNum || !prev.text.equals(item.text)) {
                        changed = true;
                    }
                }
            }
            if (changed) scheduleFlush(400L);
        } catch (Throwable t) {
            Log.w(TAG, "QUIZ_HELPER onQuestion " + t);
        }
    }

    /** A single choice object, read by the screen when it renders the option. */
    public static void onChoice(Object choice) {
        try {
            if (choice == null) return;
            boolean changed = false;
            synchronized (LOCK) {
                checkSessionExpiryLocked();
                int cId = System.identityHashCode(choice);
                Integer optNum = sChoiceOptionNum.get(cId);
                Integer qId = sChoiceToQuestion.get(cId);
                if (optNum == null) {
                    int index = sSeenChoices.size();
                    optNum = (index % 4) + 1;
                    qId = -((index / 4) + 1);
                    sSeenChoices.add(cId);
                    sChoiceOptionNum.put(cId, optNum);
                    sChoiceToQuestion.put(cId, qId);
                }
                String clean = processChoiceLocked(choice, optNum);
                if (clean != null && qId != null) {
                    AnswerItem item = new AnswerItem(optNum, clean);
                    AnswerItem prev = sQuestionsMap.put(qId, item);
                    if (prev == null || prev.optionNum != item.optionNum || !prev.text.equals(item.text)) {
                        changed = true;
                    }
                }
            }
            if (changed) scheduleFlush(400L);
        } catch (Throwable t) {
            Log.w(TAG, "QUIZ_HELPER onChoice " + t);
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Model walking
    // ---------------------------------------------------------------------------------------------

    private static void checkSessionExpiryLocked() {
        long now = System.currentTimeMillis();
        if (now - sSessionStart > SESSION_MS) {
            sQuestionsMap.clear();
            sChoiceOptionNum.clear();
            sChoiceToQuestion.clear();
            sCleanText.clear();
            sSeenChoices.clear();
            sSessionStart = now;
        }
    }

    /** Marks the correct choice(s) of a question; returns the (first) correct AnswerItem with 1-based option index. */
    private static AnswerItem processQuestionLocked(Object question) throws IllegalAccessException {
        if (question == null) return null;
        int qId = System.identityHashCode(question);
        AnswerItem found = null;
        int pos = 0;
        for (Object element : collectionElements(question)) {
            if (element == null || correctField(element.getClass()) == null) continue;
            pos++;
            int cId = System.identityHashCode(element);
            sChoiceOptionNum.put(cId, pos);
            sChoiceToQuestion.put(cId, qId);
            sSeenChoices.add(cId);
            String clean = processChoiceLocked(element, pos);
            if (clean != null && found == null) {
                found = new AnswerItem(pos, clean);
            }
        }
        return found;
    }

    /**
     * If this choice is the correct one, formats its visible text as {@code "<optNum> - <clean>"}
     * and returns the original clean text.
     */
    private static String processChoiceLocked(Object choice, int optNum) throws IllegalAccessException {
        if (choice == null) return null;
        Field correct = correctField(choice.getClass());
        if (correct == null || !truthy(correct.get(choice))) return null;
        Field textField = textField(choice);
        if (textField == null) return null;
        String current = (String) textField.get(choice);
        if (current == null || current.trim().isEmpty()) return null;

        int cId = System.identityHashCode(choice);
        String clean = sCleanText.get(cId);
        if (clean == null) {
            clean = stripDecorations(current);
            sCleanText.put(cId, clean);
        }
        String formatted = optNum + " - " + clean;
        if (!formatted.equals(current)) {
            textField.set(choice, formatted);
        }
        return clean;
    }

    private static String stripDecorations(String text) {
        return text.replaceFirst("^[1-9]\\s*-\\s*", "").trim();
    }

    /** Elements of every Collection field declared on the object's class hierarchy. */
    private static List<Object> collectionElements(Object owner) {
        List<Object> out = new ArrayList<>();
        for (Field f : allFields(owner.getClass())) {
            if (!Collection.class.isAssignableFrom(f.getType())) continue;
            try {
                Object value = f.get(owner);
                if (value instanceof Collection) out.addAll((Collection<?>) value);
            } catch (Throwable ignored) {}
        }
        return out;
    }

    private static Field correctField(Class<?> cls) {
        synchronized (sCorrectField) {
            Object cached = sCorrectField.get(cls);
            if (cached != null) return cached == NONE ? null : (Field) cached;
            Field result = null;
            for (Field f : allFields(cls)) {
                String key = jsonKey(f);
                for (String k : CORRECT_KEYS) {
                    if (k.equalsIgnoreCase(key) || k.equalsIgnoreCase(f.getName())) { result = f; break; }
                }
                if (result != null) break;
            }
            sCorrectField.put(cls, result == null ? NONE : result);
            return result;
        }
    }

    private static Field textField(Object choice) {
        Class<?> cls = choice.getClass();
        synchronized (sTextField) {
            Object cached = sTextField.get(cls);
            if (cached != null) return cached == NONE ? null : (Field) cached;
        }
        List<Field> strings = new ArrayList<>();
        for (Field f : allFields(cls)) if (f.getType() == String.class) strings.add(f);

        Field result = null;
        for (String k : TEXT_KEYS) {
            for (Field f : strings) {
                if (k.equalsIgnoreCase(jsonKey(f)) || k.equalsIgnoreCase(f.getName())) { result = f; break; }
            }
            if (result != null) break;
        }
        if (result != null) {
            synchronized (sTextField) { sTextField.put(cls, result); }
            return result;
        }
        // Fallback (not cached, value dependent): the first String that looks like visible text.
        for (Field f : strings) {
            String key = jsonKey(f).toLowerCase();
            if (key.contains("id") || key.contains("url") || key.contains("image") || key.contains("img")
                || key.contains("icon") || key.contains("color") || key.contains("type")) continue;
            try {
                String v = (String) f.get(choice);
                if (v == null || v.trim().isEmpty() || v.startsWith("http") || v.matches("\\d+")) continue;
                return f;
            } catch (Throwable ignored) {}
        }
        return null;
    }

    private static List<Field> allFields(Class<?> cls) {
        List<Field> out = new ArrayList<>();
        for (Class<?> c = cls; c != null && c != Object.class; c = c.getSuperclass()) {
            String n = c.getName();
            if (n.startsWith("java.") || n.startsWith("android.") || n.startsWith("kotlin.")) break;
            for (Field f : c.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                try { f.setAccessible(true); } catch (Throwable ignored) { continue; }
                out.add(f);
            }
        }
        return out;
    }

    /** The JSON key Gson uses for this field (@SerializedName value, else the field name). */
    private static String jsonKey(Field f) {
        try {
            for (Annotation a : f.getDeclaredAnnotations()) {
                if (a.annotationType().getName().endsWith("SerializedName")) {
                    Method value = a.annotationType().getMethod("value");
                    Object v = value.invoke(a);
                    if (v instanceof String && !((String) v).isEmpty()) return (String) v;
                }
            }
        } catch (Throwable ignored) {}
        return f.getName();
    }

    private static boolean truthy(Object v) {
        if (v instanceof Boolean) return (Boolean) v;
        if (v instanceof Number) return ((Number) v).intValue() == 1;
        if (v instanceof String) return "true".equalsIgnoreCase((String) v) || "1".equals(v);
        return false;
    }

    // ---------------------------------------------------------------------------------------------
    // Output
    // ---------------------------------------------------------------------------------------------

    private static void scheduleFlush(long delayMs) {
        MAIN.removeCallbacks(FLUSH);
        MAIN.postDelayed(FLUSH, delayMs);
    }

    private static void flush() {
        try {
            List<AnswerItem> items;
            synchronized (LOCK) {
                items = new ArrayList<>(sQuestionsMap.values());
            }
            if (items.isEmpty()) return;

            StringBuilder keyDigits = new StringBuilder();
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                AnswerItem item = items.get(i);
                keyDigits.append(item.optionNum);
                lines.add("Q" + (i + 1) + ": " + item.optionNum + " - " + item.text);
            }

            String key = keyDigits.toString();
            String signature = key + "\n" + TextUtils.join("\n", lines);
            if (signature.equals(sLastNotified)) return;
            sLastNotified = signature;

            Context ctx = context();
            if (ctx == null) {
                Log.w(TAG, "QUIZ_HELPER no context for notification");
                return;
            }
            WorkerNotify.notifyQuizAnswers(ctx, key, lines);

            String toastMsg = "Answer Key: " + key + "\n" + TextUtils.join(" | ", lines);
            Toast.makeText(ctx, toastMsg, Toast.LENGTH_LONG).show();
            Log.i(TAG, "QUIZ_HELPER key=" + key + " answers=" + lines.size());
        } catch (Throwable t) {
            Log.w(TAG, "QUIZ_HELPER flush " + t);
        }
    }

    private static Context context() {
        if (sContext != null) return sContext;
        try {
            Object app = Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            if (app instanceof Context) sContext = ((Context) app).getApplicationContext();
        } catch (Throwable ignored) {}
        return sContext;
    }
}
