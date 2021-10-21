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

        // TODO: I've seeded some info here. If you uncomment these, it will duplicate upon several
        // TODO: runs. This will obviously be handled differently eventually.
        if (!findABetterWayOfCheckingThis) {
            /*
            saveRestaurant("Guagino's Pizzeria","Italo Blvd. 24", "Italian", "444 444");
            saveRestaurant("Sander Kebab","Oslovein 12", "Nattmat", "4224 444");
            saveRestaurant("Pa99++","Universitetsgate ", "Café", "222 444");
            saveRestaurant("Alonzoz's Pizzeria","Italo Blvd. 21", "Italian", "555 555");
            saveRestaurant("Gabagool","Italo Blvd. 10", "Italian", "222 1212");
            saveRestaurant("Café Mehren","Bøhmergaten 12", "Café", "42 4114");
            */
            showRestaurants();
            findABetterWayOfCheckingThis = true;
        }

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

    // TODO: Move SaveRestaurant to RegisterRestaurant class
    private void saveRestaurant(String name_in, String address_in, String type_in, String phone_in){
        final String name = name_in;
        final String address = address_in;
        final String type = type_in;
        final String phone = phone_in;

        class SaveRestaurant extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids){

                Restaurant r = new Restaurant();
                r.setName(name);
                r.setAddress(address);
                r.setType(type);
                r.setPhone(phone);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .restaurantDao()
                        .insert(r);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                //Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        }
        SaveRestaurant sr = new SaveRestaurant();
        sr.execute();
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
                Toast.makeText(getApplicationContext(), "Shown", Toast.LENGTH_LONG).show();

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
