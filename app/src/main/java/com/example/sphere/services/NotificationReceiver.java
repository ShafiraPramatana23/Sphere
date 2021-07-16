package com.example.sphere.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class NotificationReceiver extends BroadcastReceiver {
    public static final String CHANNEL_ID = "NotifRiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("masuk sini");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Sphere Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);

            Intent serviceIntent = new Intent(context, MyService.class);
            ContextCompat.startForegroundService(context, serviceIntent);
        }
    }
}
