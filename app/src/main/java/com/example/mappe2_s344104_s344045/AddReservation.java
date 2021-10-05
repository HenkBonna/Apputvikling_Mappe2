package com.example.mappe2_s344104_s344045;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddReservation extends AppCompatActivity {
    private Spinner restaurants;
    private Spinner friendsSpinner;
    private List<Friend> friendList;
    private EditText date;
    private EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        restaurants = findViewById(R.id.spinner_restaurant);
        friendsSpinner = findViewById(R.id.spinner_friends);
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        friendList = new ArrayList<>();
        //TODO fill spinners with restaurants and friends
    }

    public void saveReservation(View view) {
        //TODO implement method
        Restaurant restaurant = new Restaurant("Xiao's Chinese", "Osloveien 82", "22xxxxxx", "Kinesisk"); //TODO change to get value from spinner
        String stringDate = date.getText().toString();
        String stringTime = time.getText().toString();

        Reservation reservation = new Reservation(restaurant, stringDate, stringTime, friendList);
        //TODO save to DB
    }


    public void addToReservation(View view) {
        Friend friend = new Friend("Fornavn", "Etternavn", "44881133");//TODO change for getValue or something
        friendList.add(friend);
    }
}
