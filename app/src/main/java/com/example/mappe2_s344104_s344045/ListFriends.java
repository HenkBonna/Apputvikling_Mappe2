package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class ListFriends extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private FloatingActionButton fab;
    private BottomNavigationView nav;
    private ImageButton imgBtn;

    FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nav = (BottomNavigationView) findViewById(R.id.nav_view);
        imgBtn = (ImageButton) findViewById(R.id.settings_btn);

        textView.setText("Venner");

        nav.setSelectedItemId(R.id.navigation_friends);

        showFriends();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Venner":
                        //Intent i = new Intent(ListFriends.this, ListFriends.class);
                        //startActivity(i);
                        //finish();
                        break;
                    case "Reservasjoner":
                        Intent i2 = new Intent(ListFriends.this, ListReservations.class);
                        startActivity(i2);
                        finish();
                        break;
                    case "Restauranter":
                        Intent i3 = new Intent(ListFriends.this, ListRestaurants.class);
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
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Settings.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        showFriends();
    }

    private void showFriends(){
        class ShowFriends extends AsyncTask<Void, Void, List<Friend>>{
            @Override
            protected List<Friend> doInBackground(Void... voids){
                List<Friend> allFriends = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .friendDao()
                        .getAll();
                return allFriends;
            }
            @Override
            protected void onPostExecute(List<Friend> allFriends){
                super.onPostExecute(allFriends);

                // REDUNDANT //////
                String out="";
                for (Friend f : allFriends){
                    out = f.getFirstname() + " " + f.getLastname() + " (" + f.getPhone() + ")";
                }
                //textView.setText(out);
                System.out.println("RESULTAT: "+ out);
                //////////////////
                displayFriends(allFriends);
            }
        }
        ShowFriends sf = new ShowFriends();
        sf.execute();
    }

    public void displayFriends(List<Friend> friendList){

        String[] temp = new String[friendList.size()];
        int i = 0;
        for (Friend f : friendList){
            String tempString = f.getFirstname() + " " + f.getLastname() + " (" + f.getPhone() + ")";
            temp[i] = tempString;
            i++;
        }

        adapter = new FriendAdapter(this,
                R.layout.friend_entry, friendList);
        listView.setAdapter(adapter);
    }
}
