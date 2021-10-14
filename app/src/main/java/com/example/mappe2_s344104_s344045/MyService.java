package com.example.mappe2_s344104_s344045;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    @Override
    public IBinder onBind (Intent arg0){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("TAG", "Started service");
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){


        return super.onStartCommand(intent, flags, startId);
    }
}
