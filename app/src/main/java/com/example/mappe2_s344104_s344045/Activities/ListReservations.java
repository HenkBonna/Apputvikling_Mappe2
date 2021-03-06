package com.example.mappe2_s344104_s344045.Activities;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mappe2_s344104_s344045.Adapters.ReservationAdapter;
import com.example.mappe2_s344104_s344045.Database.DBHandler;
import com.example.mappe2_s344104_s344045.Database.DatabaseClient;
import com.example.mappe2_s344104_s344045.R;
import com.example.mappe2_s344104_s344045.Models.Reservation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class ListReservations extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private FloatingActionButton fab;
    private BottomNavigationView nav;
    private ImageButton imgBtn;

    private DBHandler db;
    public final static String PREFS = "PrefsFile";

    private SharedPreferences settingsPref;
    private SharedPreferences.Editor editor;

    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DBHandler(this);
        settingsPref = getSharedPreferences(PREFS, MODE_PRIVATE);
        editor = settingsPref.edit();

        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nav = (BottomNavigationView) findViewById(R.id.nav_view);
        imgBtn = (ImageButton) findViewById(R.id.settings_btn);

        textView.setText("Reservasjoner");
        
        nav.setSelectedItemId(R.id.navigation_reservations);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Venner":
                        Intent i = new Intent(ListReservations.this, ListFriends.class);
                        startActivity(i);
                        finish();
                        break;
                    case "Reservasjoner":
                        break;
                    case "Restauranter":
                        Intent i3 = new Intent(ListReservations.this, ListRestaurants.class);
                        startActivity(i3);
                        finish();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterReservation.class); //TODO: Undo this thingy
                startActivity(i);
            }
        });
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Settings.class);
                startActivity(i);
            }
        });

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
        } else {
            Intent intent = new Intent();
            intent.setAction("MY_BROADCAST");
            sendBroadcast(intent, Manifest.permission.SEND_SMS);
        }

        showReservations();
        createNotificationChannel();
    }
    @Override
    public void onResume(){
        super.onResume();
        showReservations();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setAction("MY_BROADCAST");
            sendBroadcast(intent, Manifest.permission.SEND_SMS);
        } else {
            Toast.makeText(this, "Appen har ikke tilgang til ?? sende SMS",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void showReservations(){
        class ShowReservations extends AsyncTask<Void, Void, List<Reservation>>{
            @Override
            protected List<Reservation> doInBackground(Void... voids){
                List<Reservation> allReservations = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .reservationDao()
                        .getAll();
                return allReservations;
            }
            @Override
            protected void onPostExecute(List<Reservation> allReservations){
                super.onPostExecute(allReservations);
                displayReservations(allReservations);
            }
        }
        ShowReservations sr = new ShowReservations();
        sr.execute();
    }

    private void displayReservations(List<Reservation> allReservations) {
        adapter = new ReservationAdapter(this, R.layout.reservation_entry, allReservations);
        listView.setAdapter(adapter);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ChannelName@181";
            String description = " ChannelDesc@182";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("22", name, importance);
            channel.setDescription(description);
        }
    }

}
