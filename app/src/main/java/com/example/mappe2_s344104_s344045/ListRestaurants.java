package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ListRestaurants extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private FloatingActionButton fab;
    private BottomNavigationView nav;
    private ImageButton imgBtn;

    RestaurantAdapter adapter;

    private boolean findABetterWayOfCheckingThis = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nav = (BottomNavigationView) findViewById(R.id.nav_view);
        imgBtn = (ImageButton) findViewById(R.id.settings_btn);

        textView.setText("Restauranter");
        nav.setSelectedItemId(R.id.navigation_restaurants);

        showRestaurants();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Venner":
                        Intent i = new Intent(ListRestaurants.this, ListFriends.class);
                        startActivity(i);
                        finish();
                        break;
                    case "Reservasjoner":
                        Intent i2 = new Intent(ListRestaurants.this, ListReservations.class);
                        startActivity(i2);
                        finish();
                        break;
                    case "Restauranter":
                        //Intent i3 = new Intent(ListFriends.this, ListRestaurants.class);
                        //startActivity(i3);
                        //finish();
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
                Intent i = new Intent(view.getContext(), RegisterRestaurant.class);
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
        showRestaurants();
    }

    private void showRestaurants(){
        class ShowRestaurants extends AsyncTask<Void, Void, List<Restaurant>>{
            @Override
            protected List<Restaurant> doInBackground(Void... voids){
                List<Restaurant> allRestaurants = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .restaurantDao()
                        .getAll();
                return allRestaurants;
            }
            @Override
            protected void onPostExecute(List<Restaurant> allRestaurants){
                super.onPostExecute(allRestaurants);

                // REDUNDANT //////
                String out="";
                for (Restaurant r : allRestaurants){
                    out = r.getName() + ", " + r.getAddress() + " (" + r.getPhone() + ") " + r.getType();
                }
                //textView.setText(out);
                System.out.println("RESULTAT: "+ out);
                //////////////////
                displayRestaurants(allRestaurants);
            }
        }
        ShowRestaurants sf = new ShowRestaurants();
        sf.execute();
    }

    public void displayRestaurants(List<Restaurant> restaurantList){

        String[] temp = new String[restaurantList.size()];
        int i = 0;
        for (Restaurant r : restaurantList){
            String tempString = r.getName() + ", " + r.getAddress() + " (" + r.getPhone() + ") " + r.getType();
            temp[i] = tempString;
            i++;
        }

        adapter = new RestaurantAdapter(this,
                R.layout.restaurant_entry, restaurantList);
        listView.setAdapter(adapter);
    }


}
