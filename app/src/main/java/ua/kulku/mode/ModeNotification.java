package ua.kulku.mode;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import ua.kulku.mode.app.MainActivity;

/**
 * Created by andrii.lavrinenko on 29.11.2014.
 */
public class ModeNotification {

    private static final int NOTIFICATION_ID = "mode manager".hashCode();

    public static void cancel(Context context) {
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .cancel(NOTIFICATION_ID);
    }

    public static void show(Context context) {
        Mode current = ModeManager.getCurrent(context);
        String text = "(" + current.getNumber() + ") " + current.getDescription();
        PendingIntent switchActionIntent = PendingIntent.getBroadcast(
                context,
                0,
                new Intent(context, NotificationSwitchAction.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addParentStack(MainActivity.class)
                .addNextIntent(new Intent(context, MainActivity.class))
                .getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_action_accessibility)
                .setContentTitle(text)
                .setContentText(context.getString(R.string.effective_or_just_busy))
                .addAction(
                        R.drawable.ic_action_action_assignment_turned_in,
                        context.getString(R.string.forward),
                        switchActionIntent
                )
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .build();
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(NOTIFICATION_ID, notification);
    }

}
