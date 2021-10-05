package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListRestaurants extends AppCompatActivity {
    TextView textView;
    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.button);

        textView.setText("Restauranter");

        String[] temp = {"Henrik","Sander","Randi","Emilia","Manuel","Marianne","Xavier",
                "Ishmael","Møbius","Cthulu","Aelia","Whappy McDoogal","Jeff","Anton",
                "Mammy Louise"};

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list, temp);

        listView.setAdapter(adapter);


        button.setText("Legg til ny Restaurant");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterRestaurant.class);
                startActivity(i);
            }
        });
    }
}
