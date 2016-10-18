package com.example.karthickramjee.androidlab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by karthickramjee on 14/10/16.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        PendingIntent pending=PendingIntent.getActivity(context, 0, new Intent(),0);
        builder.setSmallIcon(R.drawable. notification_template_icon_bg)
                .setContentTitle("New Message")
                .setContentText("Helo World!!!")
                .setShowWhen(true)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.play)
                .setWhen(100)
                .setContentIntent(pending);

        Notification notification = builder.getNotification();
        notificationManager.notify(R.drawable.notification_template_icon_bg, notification);

    }
}
