package app.proxma.mytelenor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Intercepts Answer & Win quiz question requests in My Telenor.
 * Appends a checkmark (" ✓") to the choice text for all choices where isCorrect == true,
 * so the correct answers are clearly displayed in the UI when playing manually.
 */
public final class QuizHelper {

    private static final String TAG = "PROXMA_MT";
    private static final Set<Integer> sHookedClients = new HashSet<>();
    private static volatile boolean sInstalled = false;

    private QuizHelper() {}

    public static synchronized void install(Context context) {
        if (context == null) return;
        Context appCtx = context.getApplicationContext();

        tryHookAllClients(appCtx);

        if (!sInstalled && appCtx instanceof Application) {
            sInstalled = true;
            ((Application) appCtx).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    tryHookAllClients(activity.getApplicationContext());
                }

                @Override
                public void onActivityStarted(Activity activity) {}

                @Override
                public void onActivityResumed(Activity activity) {
                    tryHookAllClients(activity.getApplicationContext());
                }

                @Override
                public void onActivityPaused(Activity activity) {}

                @Override
                public void onActivityStopped(Activity activity) {}

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

                @Override
                public void onActivityDestroyed(Activity activity) {}
            });
            Log.i(TAG, "QUIZ_HELPER lifecycle callbacks registered");
        }
    }

    private static void tryHookAllClients(Context context) {
        // 1. Hook OkHttpClient on Retrofit API proxy
        try {
            Object api = AppSession.api();
            if (api != null) {
                attachToRetrofit(context, api);
            }
        } catch (Throwable t) {
            Log.i(TAG, "QUIZ_HOOK_API_WAIT " + t.getClass().getSimpleName());
        }

        // 2. Scan DaggerApplication singleton fields for OkHttpClient
        try {
            Class<?> appClass = Class.forName("com.telenor.pakistan.mytelenor.BaseApp.DaggerApplication");
            Object appInstance = appClass.getMethod("d").invoke(null);
            if (appInstance != null) {
                scanAndAttach(context, appInstance);
            }
        } catch (Throwable t) {
            Log.i(TAG, "QUIZ_HOOK_APP_WAIT " + t.getClass().getSimpleName());
        }
    }

    private static void attachToRetrofit(Context context, Object api) {
        try {
            InvocationHandler ih = Proxy.getInvocationHandler(api);
            for (Field f : ih.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                Object val = f.get(ih);
                if (val != null && val.getClass().getName().contains("Retrofit")) {
                    for (Field rf : val.getClass().getDeclaredFields()) {
                        rf.setAccessible(true);
                        Object callFactory = rf.get(val);
                        if (callFactory != null && callFactory.getClass().getName().contains("OkHttpClient")) {
                            injectInterceptor(context, callFactory);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            Log.i(TAG, "QUIZ_RETROFIT_ERR " + t);
        }
    }

    private static void scanAndAttach(Context context, Object target) {
        if (target == null) return;
        try {
            for (Field f : target.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                Object obj = f.get(target);
                if (obj != null && obj.getClass().getName().contains("OkHttpClient")) {
                    injectInterceptor(context, obj);
                }
            }
        } catch (Throwable ignored) {}
    }

    @SuppressWarnings("unchecked")
    private static synchronized void injectInterceptor(Context context, Object okHttpClient) {
        if (okHttpClient == null) return;
        int id = System.identityHashCode(okHttpClient);
        if (sHookedClients.contains(id)) return;

        try {
            Field interceptorsField = null;
            for (Field f : okHttpClient.getClass().getDeclaredFields()) {
                if ("interceptors".equals(f.getName()) || 
                    (List.class.isAssignableFrom(f.getType()) && !f.getName().equals("networkInterceptors"))) {
                    interceptorsField = f;
                    break;
                }
            }
            if (interceptorsField == null) {
                interceptorsField = okHttpClient.getClass().getDeclaredField("interceptors");
            }
            interceptorsField.setAccessible(true);
            Object listObj = interceptorsField.get(okHttpClient);

            if (listObj instanceof List) {
                List targetList = (List) listObj;
                // UnmodifiableList backing list extraction
                if (listObj.getClass().getName().contains("Unmodifiable")) {
                    for (Field uf : listObj.getClass().getDeclaredFields()) {
                        if (Collection.class.isAssignableFrom(uf.getType()) || List.class.isAssignableFrom(uf.getType())) {
                            uf.setAccessible(true);
                            Object inner = uf.get(listObj);
                            if (inner instanceof List) {
                                targetList = (List) inner;
                                break;
                            }
                        }
                    }
                }

                Object interceptor = createInterceptor(context);
                if (interceptor != null) {
                    targetList.add(0, interceptor);
                    sHookedClients.add(id);
                    Log.i(TAG, "QUIZ_INTERCEPTOR_INJECTED client=" + id);
                }
            }
        } catch (Throwable t) {
            Log.w(TAG, "QUIZ_INJECT_ERR " + t);
        }
    }

    private static Object createInterceptor(final Context context) {
        try {
            Class<?> interceptorClass = Class.forName("okhttp3.Interceptor");
            return Proxy.newProxyInstance(
                interceptorClass.getClassLoader(),
                new Class<?>[]{interceptorClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("intercept".equals(method.getName()) && args != null && args.length == 1) {
                            return handleIntercept(context, args[0]);
                        }
                        if ("equals".equals(method.getName())) {
                            return proxy == (args != null && args.length > 0 ? args[0] : null);
                        }
                        if ("hashCode".equals(method.getName())) {
                            return System.identityHashCode(proxy);
                        }
                        if ("toString".equals(method.getName())) {
                            return "ProxmaQuizInterceptor";
                        }
                        return method.invoke(this, args);
                    }
                }
            );
        } catch (Throwable t) {
            Log.w(TAG, "CREATE_INTERCEPTOR_ERR " + t);
            return null;
        }
    }

    private static Object handleIntercept(Context context, Object chain) throws Throwable {
        Object request = chain.getClass().getMethod("request").invoke(chain);
        Object httpUrl = request.getClass().getMethod("url").invoke(request);
        String url = httpUrl != null ? httpUrl.toString() : "";

        Method proceedMethod = null;
        for (Method m : chain.getClass().getMethods()) {
            if ("proceed".equals(m.getName()) && m.getParameterTypes().length == 1) {
                proceedMethod = m;
                break;
            }
        }
        if (proceedMethod == null) {
            throw new IllegalStateException("Chain.proceed not found");
        }

        Object response = proceedMethod.invoke(chain, request);

        // Check for quiz trivia questions endpoint
        if (url.contains("qplayandwin") || url.contains("questions/daily") || url.contains("getTrivialQuestion")) {
            try {
                Object body = response.getClass().getMethod("body").invoke(response);
                if (body != null) {
                    String rawJson = (String) body.getClass().getMethod("string").invoke(body);
                    List<String> answers = extractAnswersList(rawJson);
                    if (answers != null && !answers.isEmpty()) {
                        WorkerNotify.notifyQuizAnswers(context, answers);
                    }
                    String modifiedJson = modifyQuizJson(rawJson);
                    if (modifiedJson != null) {
                        Object mediaType = body.getClass().getMethod("contentType").invoke(body);
                        Object newBody = createResponseBody(body.getClass(), mediaType, modifiedJson);
                        if (newBody != null) {
                            Object newBuilder = response.getClass().getMethod("newBuilder").invoke(response);
                            newBuilder.getClass().getMethod("body", body.getClass()).invoke(newBuilder, newBody);
                            response = newBuilder.getClass().getMethod("build").invoke(newBuilder);

                            Log.i(TAG, "QUIZ_INTERCEPT_SUCCESS: answer key marked with ✓");
                            showAnswerToast(context);
                        }
                    }
                }
            } catch (Throwable t) {
                Log.w(TAG, "QUIZ_INTERCEPT_BODY_ERR " + t);
            }
        }

        return response;
    }

    private static Object createResponseBody(Class<?> responseBodyClass, Object mediaType, String content) {
        try {
            for (Method m : responseBodyClass.getMethods()) {
                if (Modifier.isStatic(m.getModifiers()) && "create".equals(m.getName())) {
                    Class<?>[] params = m.getParameterTypes();
                    if (params.length == 2) {
                        if (params[0].isAssignableFrom(mediaType != null ? mediaType.getClass() : Object.class) &&
                            params[1] == String.class) {
                            return m.invoke(null, mediaType, content);
                        }
                    }
                }
            }

            // OkHttp 4 Companion fallback: ResponseBody.Companion.create(content, mediaType)
            Class<?> compClass = Class.forName("okhttp3.ResponseBody$Companion");
            Field compField = responseBodyClass.getField("Companion");
            Object comp = compField.get(null);
            for (Method m : compClass.getMethods()) {
                if ("create".equals(m.getName()) && m.getParameterTypes().length == 2) {
                    Class<?>[] params = m.getParameterTypes();
                    if (params[0] == String.class) {
                        return m.invoke(comp, content, mediaType);
                    }
                }
            }
        } catch (Throwable t) {
            Log.w(TAG, "CREATE_RESPONSE_BODY_ERR " + t);
        }
        return null;
    }

    private static String modifyQuizJson(String rawJson) {
        try {
            JSONObject root = new JSONObject(rawJson);
            JSONObject data = root.optJSONObject("data");
            if (data == null) return null;
            JSONArray questions = data.optJSONArray("triviaQuestions");
            if (questions == null || questions.length() == 0) return null;

            boolean modified = false;
            for (int i = 0; i < questions.length(); i++) {
                JSONObject q = questions.optJSONObject(i);
                if (q == null) continue;
                JSONArray choices = q.optJSONArray("choices");
                if (choices == null) continue;
                for (int j = 0; j < choices.length(); j++) {
                    JSONObject c = choices.optJSONObject(j);
                    if (c == null) continue;
                    if (c.optBoolean("isCorrect", false)) {
                        String text = c.optString("choice", "");
                        if (!text.endsWith("✓") && !text.endsWith("✅")) {
                            c.put("choice", text + "  ✓");
                            modified = true;
                        }
                    }
                }
            }
            return modified ? root.toString() : null;
        } catch (Throwable t) {
            Log.w(TAG, "MODIFY_QUIZ_JSON_ERR " + t);
            return null;
        }
    }

    public static List<String> extractAnswersList(String rawJson) {
        try {
            JSONObject root = new JSONObject(rawJson);
            JSONObject data = root.optJSONObject("data");
            if (data == null) return null;
            JSONArray questions = data.optJSONArray("triviaQuestions");
            if (questions == null || questions.length() == 0) return null;

            List<String> list = new java.util.ArrayList<>();
            for (int i = 0; i < questions.length(); i++) {
                JSONObject q = questions.optJSONObject(i);
                if (q == null) continue;
                JSONArray choices = q.optJSONArray("choices");
                if (choices == null) continue;
                String correctChoice = null;
                for (int j = 0; j < choices.length(); j++) {
                    JSONObject c = choices.optJSONObject(j);
                    if (c != null && c.optBoolean("isCorrect", false)) {
                        correctChoice = c.optString("choice", "");
                        break;
                    }
                }
                if (correctChoice != null && !correctChoice.isEmpty()) {
                    list.add(correctChoice);
                }
            }
            return list;
        } catch (Throwable t) {
            Log.w(TAG, "EXTRACT_ANSWERS_ERR " + t);
            return null;
        }
    }

    private static void showAnswerToast(final Context context) {
        if (context == null) return;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(context, "Answer & Win: Correct options marked with ✓", Toast.LENGTH_LONG).show();
                } catch (Throwable ignored) {}
            }
        });
    }
}

