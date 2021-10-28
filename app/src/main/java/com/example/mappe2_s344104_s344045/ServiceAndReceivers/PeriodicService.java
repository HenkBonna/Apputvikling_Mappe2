package com.example.mappe2_s344104_s344045.ServiceAndReceivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.example.mappe2_s344104_s344045.Activities.ListReservations;

import java.util.Calendar;

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

        settings = getSharedPreferences(ListReservations.PREFS, MODE_PRIVATE);
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
        if (alarmManager != null) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, (int) prefHour);
            cal.set(Calendar.MINUTE, (int) prefMinute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
            }
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
        }
    }
}
