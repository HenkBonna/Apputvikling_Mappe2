package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListRestaurants extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        textView.setText("Restauranter");

        String[] temp = {"Guaginos","Sanders Pizzaria","MickyD","Birgers"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, temp);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterRestaurant.class);
                startActivity(i);
            }
        });
    }
}
