package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class ListReservations extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private FloatingActionButton fab;
    private BottomNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nav = (BottomNavigationView) findViewById(R.id.nav_view);

        textView.setText("Dine Reservasjoner");

        // TODO: Replace with reading from _db
        // FILL with Tables
        String[] temp = {"Reservasjon 1","Reservasjon 2"};
        // TODO: Look into ArrayAdapters, to create better-looking listitems: vogella.com/tutorials/AndroidListView/article.html
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, temp);
        listView.setAdapter(adapter);

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
                        //Intent i2 = new Intent(ListReservations.this, ListReservations.class);
                        //startActivity(i2);
                        //finish();
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
                Intent i = new Intent(view.getContext(), RegisterFriend.class);
                startActivity(i);
            }
        });
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fillTable();
    }

    private void fillTable() {
        ListView listView = findViewById(R.id.reservationList);
        //TODO create tableRows and add to tablelayout
    }

    public void make_reservation(View view) {
        Intent i = new Intent(view.getContext(), AddReservation.class);
        startActivity(i);
        finish();
    }
     */
}
