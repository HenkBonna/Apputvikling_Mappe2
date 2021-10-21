package com.example.mappe2_s344104_s344045;

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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddReservation extends AppCompatActivity {
    private Spinner restaurants;
    private Spinner friendsSpinner;
    private FriendsList friendList;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Restaurant restaurant;
    private String date;
    private String time;
    private Button button;
    private ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        restaurants = findViewById(R.id.spinner_restaurant);
        friendsSpinner = findViewById(R.id.spinner_friends);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.reservationDate);
        friendList = new FriendsList();
        datePicker.setMinDate(Calendar.getInstance().getTimeInMillis());
        timePicker.setIs24HourView(true);
        timePicker.setHour(12);
        timePicker.setMinute(0);
        button = findViewById(R.id.dateButton);
        listView = findViewById(R.id.table_friends);
        fillSpinners();

        friendsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3){
                addToReservation(friendsSpinner.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });
        restaurants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3){
                restaurant = (Restaurant) restaurants.getSelectedItem();
                restaurants.setPrompt(restaurant.getName());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker tp, int i, int i1) {
                time = timePicker.getHour() + ":" + timePicker.getMinute();
                Log.e("Time", time);
            }
        });

        DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker dp, int i, int i1, int i2) {
                date = datePicker.getDayOfMonth() + "." + (datePicker.getMonth()+1) + "." + datePicker.getYear();
                Log.e("DATE", date);
            }
        };
        datePicker.init(2000,0,1, onDateChangedListener);

        //datePicker.setOnDateChangedListener(onDateChangedListener);
    }

    public void saveReservation(View view) {
        //TODO implement method
        //Restaurant restaurant = new Restaurant("Xiao's Chinese", "Osloveien 82", "22xxxxxx", "Kinesisk"); //TODO change to get value from spinner

        if (restaurant != null){
            if (date != null){
                if (time != null){
                    Reservation reservation = new Reservation(restaurant, date, time, friendList);

                } else {
                    Toast.makeText(this,"Ingen tid valgt", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ingen dato valgt", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingen restaurant valgt", Toast.LENGTH_SHORT).show();
        }
        //TODO save to DB
    }

    public void fillSpinners(){
        fillRestaurants();
        fillFriends();
    }

    private void fillRestaurants() {
        class FillRestaurants extends AsyncTask<Void, Void, List<Restaurant>>{
            @Override
            protected List<Restaurant> doInBackground(Void... voids){
                List<Restaurant> allRestaurants = new ArrayList<>();
                allRestaurants.add(new Restaurant("Xiao's", "Osloveien 82", "22xxxxxx", "Kinesisk"));
                allRestaurants.add(new Restaurant("Luigi's", "Little Italy 14", "2222xxxx","Pizza"));
                        /*DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .restaurantDao()
                        .getAll();*/
                ArrayAdapter<Restaurant> restaurantArrayAdapter = new ArrayAdapter<Restaurant>(getApplicationContext(), R.layout.spinner_item, allRestaurants);
                restaurants.setAdapter(restaurantArrayAdapter);
                return allRestaurants;
            }
        }
        FillRestaurants fr = new FillRestaurants();
        fr.execute();
    }

    private void fillFriends() {
        class FillFriends extends AsyncTask<Void, Void, List<Friend>>{
            @Override
            protected List<Friend> doInBackground(Void... voids){
                /*List<Friend> allFriends = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .friendDao()
                        .getAll();*/
                List<Friend> allFriends = new ArrayList<>();

                allFriends.add(new Friend(0, "Anders", "Andersen", "47474747"));
                allFriends.add( new Friend(1, "Jonas", "Johnsen", "48484848"));
                ArrayAdapter<Friend> restaurantArrayAdapter = new ArrayAdapter<Friend>(getApplicationContext(), R.layout.spinner_item, allFriends);
                friendsSpinner.setAdapter(restaurantArrayAdapter);
                return allFriends;
            }
        }
        FillFriends ff = new FillFriends();
        ff.execute();
    }

    public void addToReservation(Object friend) {
        //Friend friend = new Friend("Fornavn", "Etternavn", "44881133");//TODO change for getValue or something
        if (friend != null) {
            Friend newFriend = (Friend) friend;
            friendList.add(newFriend);
            listView.addView();
        }
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
