package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class ListFriends extends AppCompatActivity {
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

        textView.setText("Venner");

        // TODO: Replace with reading from _db
        String[] temp = {"Henrik","Sander","Randi","Emilia","Manuel","Marianne","Xavier",
                "Ishmael","Møbius","Cthulu","Aelia","Whappy McDoogal","Jeff","Anton",
                "Mammy Louise"};
        // TODO: Look into ArrayAdapters, to create better-looking listitems: vogella.com/tutorials/AndroidListView/article.html
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, temp);
        listView.setAdapter(adapter);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
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
}
