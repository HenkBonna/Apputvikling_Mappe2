package com.example.mappe2_s344104_s344045;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

        List<Reservation> reservations = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                .reservationDao()
                .getAll();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        String today = dateFormat.format(cal.getTime());
        for (Reservation r : reservations){
            if (r.getDate().equals(today)){
                String message = settings.getString("standard_message", "Husk reservasjon i kveld! ")
                        + r.getRestaurant() + " kl " + r.getTime();
                FriendsList friendsList = r.getFriends();
                List<Friend> friends = friendsList.getFriends();

                for (Friend f : friends) {
                    String number = f.getPhone();
                    smsManager.sendTextMessage(number,
                            null,
                            message,
                            null,
                            null);
                    Log.e("MESSAGE", "Message sent");
                }
                break;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
