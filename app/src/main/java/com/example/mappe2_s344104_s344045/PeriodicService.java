package com.example.mappe2_s344104_s344045;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PeriodicService extends Service {
    private static AlarmManager alarmManager;
    private SharedPreferences settings;
    private static PendingIntent pi;
    private static long prefHour;
    private static long prefMinute;



    @Override
    public IBinder onBind (Intent arg0){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        Log.v("TAG", "Started service");
        settings = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        if (settings.getBoolean("sms_enabled", false)) {
            Toast.makeText(getApplicationContext(), "Service running", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(this, MyService.class);
        pi = PendingIntent.getService(this, 0, i, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        prefHour = settings.getInt("hour", 8);
        prefMinute = settings.getInt("minute", 0);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if (settings.getBoolean("sms_enabled", false)) {
            alarmManager.cancel(pi);

            startAlarm();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static void cancelAlarm(){
        if (alarmManager != null) {
            alarmManager.cancel(pi);
        }
    }
    public static void startAlarm() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, (int) prefHour);
        cal.set(Calendar.MINUTE, (int) prefMinute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long millisPerDay = 24*60*60*1000;
        Log.e("Alarm start", "" + cal.getTime());
        if (cal.getTimeInMillis() < System.currentTimeMillis()) {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
            Log.e("New alarm start", "" + cal.getTime());
        }
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }
}
