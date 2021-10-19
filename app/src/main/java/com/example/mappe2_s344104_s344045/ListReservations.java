package com.example.mappe2_s344104_s344045;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class ListReservations extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private FloatingActionButton fab;
    private BottomNavigationView nav;
    private ImageButton imgBtn;

    private DBHandler db;
    public final static String PREFS = "PrefsFile";

    ArrayAdapter<String> adapter;

    private SharedPreferences settingsPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DBHandler(this);
        settingsPref = getSharedPreferences(PREFS, MODE_PRIVATE);
        editor = settingsPref.edit();
        startService(new Intent(this, MyService.class));


        textView = (TextView) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        nav = (BottomNavigationView) findViewById(R.id.nav_view);
        imgBtn = (ImageButton) findViewById(R.id.settings_btn);

        textView.setText("Reservasjoner");

        // TODO: Replace with reading from _db
        // FILL with Tables
        //String[] temp = {"Reservasjon 1","Reservasjon 2"};
        // TODO: Look into ArrayAdapters, to create better-looking listitems: vogella.com/tutorials/AndroidListView/article.html



        // TODO: IMPORTANT!!!!!!!!!! I've implemented the DB methods for friend here, as it seemed easiest. FIX THIS

        saveFriend("Espen","Askeladden","12345678");
        saveFriend("Per","Askeladden","12543678");
        saveFriend("Pål","Askeladden","12365678");
        saveFriend("Marina","Diamandis","22345678");
        saveFriend("Djivan","Gasparyan","25252525");
        saveFriend("Babatunde","Olatunji","47474747");
        saveFriend("Annie","Clark","15151515");
        saveFriend("Philip","Glass","35353535");
        saveFriend("Ali","Farka","11111111");
        saveFriend("Ahmad","Jamal","73735356");
        saveFriend("Gerard","Schwarz","35353535");

        showFriends();


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
                //saveFriend();
                //showFriends();
                Intent i = new Intent(view.getContext(), RegisterFriend.class); //TODO: Undo this thingy
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

    // TODO: Move SaveFriend to RegisterFriend class
    private void saveFriend(String fname_in, String lname_in, String phone_in){
        final String fname = fname_in;
        final String lname = lname_in;
        final String phone = phone_in;

        class SaveFriend extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids){

                Friend f = new Friend();
                f.setFirstname(fname);
                f.setLastname(lname);
                f.setPhone(phone);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .friendDao()
                        .insert(f);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        }
        SaveFriend sf = new SaveFriend();
        sf.execute();
    }

    private void showFriends(){
        class ShowFriends extends AsyncTask<Void, Void, List<Friend> >{
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
                Toast.makeText(getApplicationContext(), "Shown", Toast.LENGTH_LONG).show();

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

        adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, temp);
        listView.setAdapter(adapter);
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
