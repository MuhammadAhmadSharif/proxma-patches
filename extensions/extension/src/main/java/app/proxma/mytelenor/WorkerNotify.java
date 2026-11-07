package app.proxma.mytelenor;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public final class WorkerNotify {

    private static final String TAG = "PROXMA_MT";
    private static final String CHANNEL_ID = "proxma_mt_auto";
    private static final String CHANNEL_NAME = "My Telenor Auto";
    private static final int NOTIF_ID = 1004;
    private static final int NOTIF_QUIZ_ID = 1006;

    private WorkerNotify() {}

    public static void notifyStatus(Context context, String streakStatus, String quizStatus, String nextAction) {
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                if (context.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
                    return;
                }
            }

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm == null) return;

            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("My Telenor auto-claim status");
                nm.createNotificationChannel(channel);
            }

            int icon = context.getApplicationInfo().icon;
            if (icon == 0) return;

            String body = "Streak: " + streakStatus
                + "\nQuiz: " + quizStatus
                + "\nNext: " + nextAction;

            Notification.Builder builder;
            if (Build.VERSION.SDK_INT >= 26) {
                builder = new Notification.Builder(context, CHANNEL_ID);
            } else {
                builder = new Notification.Builder(context);
            }

            builder.setSmallIcon(icon)
                .setContentTitle("My Telenor Auto-Claim")
                .setContentText(body)
                .setStyle(new Notification.BigTextStyle().bigText(body))
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

            if (Build.VERSION.SDK_INT >= 26) {
                builder.setTimeoutAfter(72 * 60 * 60 * 1000L);
            }

            nm.notify(NOTIF_ID, builder.build());
        } catch (Throwable t) {
            Log.w(TAG, "notifyStatus failed: " + t);
        }
    }

    public static void notifyQuizAnswers(Context context, java.util.List<String> answers) {
        notifyQuizAnswers(context, "", answers);
    }

    public static void notifyQuizAnswers(Context context, String answerKey, java.util.List<String> answers) {
        if (context == null || answers == null || answers.isEmpty()) return;
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                if (context.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
                    return;
                }
            }

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm == null) return;

            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("My Telenor auto-claim status and quiz answers");
                nm.createNotificationChannel(channel);
            }

            int icon = context.getApplicationInfo().icon;
            if (icon == 0) return;

            boolean hasKey = answerKey != null && !answerKey.isEmpty();
            String title = hasKey ? "Answer & Win Key: " + answerKey : "Answer & Win: Quiz Answers";
            String summary = android.text.TextUtils.join(" | ", answers);

            StringBuilder sb = new StringBuilder();
            if (hasKey) {
                sb.append("Answer Key: ").append(answerKey).append("\n");
            }
            for (int i = 0; i < answers.size(); i++) {
                if (i > 0) sb.append("\n");
                sb.append(answers.get(i));
            }
            String body = sb.toString();

            Notification.Builder builder;
            if (Build.VERSION.SDK_INT >= 26) {
                builder = new Notification.Builder(context, CHANNEL_ID);
            } else {
                builder = new Notification.Builder(context);
            }

            builder.setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(summary)
                .setStyle(new Notification.BigTextStyle()
                    .setBigContentTitle(title)
                    .bigText(body))
                .setAutoCancel(true)
                .setOnlyAlertOnce(false);

            if (Build.VERSION.SDK_INT >= 26) {
                builder.setTimeoutAfter(24 * 60 * 60 * 1000L);
            }

            nm.notify(NOTIF_QUIZ_ID, builder.build());
            Log.i(TAG, "NOTIFY_QUIZ_ANSWERS posted: key=" + answerKey + " count=" + answers.size());
        } catch (Throwable t) {
            Log.w(TAG, "notifyQuizAnswers failed: " + t);
        }
    }
}
