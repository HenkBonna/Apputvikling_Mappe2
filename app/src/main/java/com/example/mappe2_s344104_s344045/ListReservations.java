package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ListReservations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reservations);
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
}
