package com.example.mappe2_s344104_s344045;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

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
        settings = getSharedPreferences(ListReservations.PREFS, MODE_PRIVATE);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        checkReservations();

        //Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, ListReservations.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        Notification notification = new NotificationCompat.Builder(this,"22")
                .setContentTitle("MinNotifikasjon")
                .setContentText("Tekst")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

        return super.onStartCommand(intent, flags, startId);
    }
    public void checkReservations(){
        class CheckReservations extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids){
                SmsManager smsManager = SmsManager.getDefault();
                List<Reservation> reservations = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .reservationDao()
                        .getAll();

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String today = dateFormat.format(cal.getTime());
                Calendar c = Calendar.getInstance();
                int day, month, year;

                for (Reservation r : reservations){
                    String[] date = r.getDate().split("\\.");
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]) - 1;
                    year = Integer.parseInt(date[2]);
                    c.set(Calendar.DAY_OF_MONTH, day);
                    c.set(Calendar.MONTH, month);
                    c.set(Calendar.YEAR, year);

                    //Checking if a reservation is old, and should be deleted
                    if (cal.getTime().after(c.getTime())){
                        deleteReservation(r);
                    } else {
                        //Checking if the reservation date matches the current date
                        if (r.getDate().equals(today)){
                            String message = settings.getString("standard_message",
                                    "Husk reservasjon i kveld! ") + " "
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
                                Log.e("MESSAGE", "Message sent to " + f.getFirstname() + " " + f.getLastname());
                            }
                        }
                    }
                }

                return null;
            }
        }
        CheckReservations cr = new CheckReservations();
        cr.execute();
    }

    private void deleteReservation(Reservation r) {
        class DeleteReservation extends AsyncTask<Reservation, Void, Void>{
            @Override
            protected Void doInBackground(Reservation... reservations){
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().reservationDao().delete(r);
                return null;
            }
        }
        DeleteReservation dr = new DeleteReservation();
        dr.execute();
    }
}
