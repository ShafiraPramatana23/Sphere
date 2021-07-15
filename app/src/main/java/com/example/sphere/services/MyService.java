package com.example.sphere.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.sphere.MainActivity;
import com.example.sphere.R;
import com.example.sphere.util.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {

    private String token = "";
    private String channel = "NotifRiver";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        getRiverById();

        return Service.START_NOT_STICKY;
    }

    private void getRiverById() {
        String uRl = "https://sphere-apps.herokuapp.com/api/river/1";
        System.out.println("URL river id nyaaa: " + uRl);
        StringRequest request = new StringRequest(Request.Method.GET,
                uRl,
                (String response) -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String height = obj.getString("height");
                        String status = obj.getString("status");
                        JSONObject objRiver = obj.getJSONObject("river");
                        String name = objRiver.getString("name");

                        if (status.equals("Bahaya")) {
                            pushNotification("1", name + " berada dalam status (" + status + ")");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("OMG: " + e.toString());
                    }
                }, error -> {
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getmInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void pushNotification(String id, String title) {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                Integer.parseInt(id), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notifManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Refresh")
                .setAutoCancel(true)
                .setContentTitle("Peringatan!")
                .setChannelId(channel)
                .setLights(0xff00ff00, 300, 100)
                .setSound(null)
                .setAutoCancel(true)
                .setContentText(title)
                .setContentIntent(pendingIntent)
                .build();

        notifManager.notify(Integer.parseInt(id), notification);
        startForeground(2, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
