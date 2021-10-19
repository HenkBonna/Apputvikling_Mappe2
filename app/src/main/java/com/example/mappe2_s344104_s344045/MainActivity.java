package com.example.mappe2_s344104_s344045;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button settings;
    private Button reservations;
    private FloatingActionButton makeReservation;
    private Button restaurants;
    private FloatingActionButton add_restaurant;
    private Button friends;
    private FloatingActionButton add_friend;
    private DBHandler db;
    public final static String PREFS = "PrefsFile";

    private SharedPreferences settingsPref;
    private SharedPreferences.Editor editor;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = findViewById(R.id.settings);
        reservations = findViewById(R.id.reservations);
        makeReservation = findViewById(R.id.make_reservation);
        restaurants = findViewById(R.id.restaurants);
        add_restaurant = findViewById(R.id.add_restaurant);
        friends = findViewById(R.id.friends);
        add_friend = findViewById(R.id.add_friend);
        db = new DBHandler(this);

        settingsPref = getSharedPreferences(PREFS, MODE_PRIVATE);
        editor = settingsPref.edit();

        startService(new Intent(this, MyService.class));
    }

    public void settings(View view) {
        Intent i = new Intent(view.getContext(), Settings.class);
        startActivity(i);
    }

    public void see_reservations(View view) {
        Intent i = new Intent(view.getContext(), ListReservations.class);
        startActivity(i);
    }

    public void see_restaurants(View view) {
        Intent i = new Intent(view.getContext(), ListRestaurants.class);
        startActivity(i);
    }

    public void add_restaurant(View view) {
        Intent i = new Intent(view.getContext(), RegisterRestaurant.class);
        startActivity(i);
    }

    public void make_reservation(View view) {
        Intent i = new Intent(view.getContext(), AddReservation.class);
        startActivity(i);
    }

    public void see_friends(View view) {
        Intent i = new Intent(view.getContext(), ListFriends.class);
        startActivity(i);
    }

    public void add_friend(View view) {
        Intent i = new Intent(view.getContext(), RegisterFriend.class);
        startActivity(i);
    }
    */

    /*
    public void add(View v){
        Restaurant restaurant = new Restaurant (name_in.getText().toString(), phone_in.getText().toString());
        db.leggTilRestaurant(restaurant);
        Log.d("Legg inn: ", "legger til restauranter");

    }
    public void show_all(View v){
        String tekst = "";
        List<Restaurant> restauranter = db.finnAlleRestauranter();

        for (Restaurant restaurant : restauranter){
            tekst = tekst + "Id: " + restaurant.get_ID() + ",Navn: "
                    + restaurant.getName() + ",Telefon: " +restaurant.getPhone();
            Log.d("Navn: ", tekst);
        }
        print.setText(tekst);

    }
    public void delete(View v){
        Long restaurantid = (Long.parseLong(id_in.getText().toString()));
        db.slettRestaurant(restaurantid);
    }
    public void update(View v){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name_in.getText().toString());
        restaurant.setPhone(phone_in.getText().toString());
        restaurant.set_ID(Long.parseLong(id_in.getText().toString()));
        db.oppdaterRestaurant(restaurant);
    }
    */

}