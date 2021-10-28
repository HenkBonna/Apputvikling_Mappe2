package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddReservation extends AppCompatActivity {
    private Spinner restaurants;
    private ListView listView;
    private FriendsList friendList;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Restaurant restaurant;
    private String date;
    private String time;
    private Button button;
    private Button addRestaurantButton;
    private Button addFriendButton;
    private FriendAdapter adapter;
    private Reservation reservation;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        restaurants = findViewById(R.id.spinner_restaurant);
        listView = findViewById(R.id.list_of_friends);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.reservationDate);
        addRestaurantButton = findViewById(R.id.add_restaurant_button);
        addFriendButton = findViewById(R.id.add_friend_button);
        friendList = new FriendsList();
        datePicker.setMinDate(Calendar.getInstance().getTimeInMillis());
        timePicker.setIs24HourView(true);
        timePicker.setHour(12);
        timePicker.setMinute(0);
        //button = findViewById(R.id.dateButton);
        fillSpinners();


        restaurants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3){
                restaurant = (Restaurant) restaurants.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker tp, int i, int i1) {
                time = timePicker.getHour() + ":" + timePicker.getMinute();
            }
        });

        DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker dp, int i, int i1, int i2) {
                date = datePicker.getDayOfMonth() + "." + (datePicker.getMonth()+1) + "." + datePicker.getYear();
            }
        };
        datePicker.init(2000,0,1, onDateChangedListener);

        //datePicker.setOnDateChangedListener(onDateChangedListener);
        if (getIntent().getExtras() != null){
            Long id = getIntent().getExtras().getLong("reservation_ID");
            getReservation(id);
        } else {
            showFriends();
        }

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterFriend.class);
                startActivity(i);
            }
        });

        addRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterRestaurant.class);
                startActivity(i);
            }
        });
    }

    private void getReservation(Long id) {
        class GetReservation extends AsyncTask<Long, Void, Void>{
            @Override
            public Void doInBackground(Long... longs){
                reservation = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .reservationDao()
                        .get(id);
                date = reservation.getDate();
                time = reservation.getTime();
                restaurant = reservation.getRestaurant();
                friendList = reservation.getFriends();
                String[] splitTime = time.split(":");
                int hour = Integer.parseInt(splitTime[0]);
                int minute = Integer.parseInt(splitTime[1]);
                String[] splitDate = date.split("\\.");
                int day = Integer.parseInt(splitDate[0]);
                int month = (Integer.parseInt(splitDate[1])) - 1;
                int year = Integer.parseInt(splitDate[2]);
                timePicker.setHour(hour);
                timePicker.setMinute(minute);
                datePicker.updateDate(year, month, day);
                return null;
            }
        }
        GetReservation gr = new GetReservation();
        gr.execute();
        showFriends();
    }

    public void saveReservation(View view) {
        class SaveReservation extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                time = timePicker.getHour() + ":" + timePicker.getMinute();
                date = datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear();
                List<Friend> items = adapter.getCheckedFriends();
                friendList.setFriends(items);
                if (reservation != null){
                    reservation.setFriends(friendList);
                    reservation.setRestaurant(restaurant);
                    reservation.setDate(date);
                    reservation.setTime(time);
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .reservationDao()
                            .update(reservation);
                } else {
                    reservation = new Reservation(restaurant, date, time, friendList);
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .reservationDao()
                            .insert(reservation);
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                finish();
            }
        }
        SaveReservation sr = new SaveReservation();
        sr.execute();
    }

    public void fillSpinners(){
        fillRestaurants();
    }

    private void fillRestaurants() {
        class FillRestaurants extends AsyncTask<Void, Void, List<Restaurant>>{
            @Override
            protected List<Restaurant> doInBackground(Void... voids){
                List<Restaurant> allRestaurants = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .restaurantDao()
                        .getAll();
                ArrayAdapter<Restaurant> restaurantArrayAdapter = new ArrayAdapter<Restaurant>(getApplicationContext(), R.layout.spinner_item, allRestaurants);
                restaurants.setAdapter(restaurantArrayAdapter);
                return allRestaurants;
            }
        }
        FillRestaurants fr = new FillRestaurants();
        fr.execute();
    }

    private void showFriends(){
        class ShowFriends extends AsyncTask<Void, Void, List<Friend>>{
            @Override
            protected List<Friend> doInBackground(Void... voids){
                List<Friend> allFriends = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .friendDao()
                        .getAll();

                displayFriends(allFriends);
                return allFriends;
            }
        }
        ShowFriends sf = new ShowFriends();
        sf.execute();
    }
    public void displayFriends(List<Friend> friends){
        List<Friend> fs = friendList.getFriends();
        for (Friend f : fs){
            for (Friend friend : friends){
                if (f.get_ID() == friend.get_ID()){
                    friend.setChecked(true);
                    break;
                }
            }
        }
        adapter = new FriendAdapter(this,
                R.layout.friend_picker, friends);
        listView.setAdapter(adapter);
    }

    public void switchPicker(View view) {
        if (datePicker.getVisibility() == View.VISIBLE) {
            datePicker.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.VISIBLE);
        } else {
            datePicker.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
        }
    }
}
