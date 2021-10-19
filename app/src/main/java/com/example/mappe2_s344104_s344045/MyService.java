package com.example.mappe2_s344104_s344045;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyService extends Service {
    SharedPreferences settings;
    @Override
    public void onCreate(){
        super.onCreate();
        settings = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage("+4746196490",
                null,
                settings.getString("standard_message", "Husk reservasjon i kveld!"),
                null,
                null);
        Log.e("MESSAGE", "Message sent");

        return super.onStartCommand(intent, flags, startId);
    }
}
