package com.example.mappe2_s344104_s344045;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name_in;
    EditText phone_in; //This performance was phoned in el-oh-el-eks-dee
    EditText address_in;
    EditText type_in;
    EditText id_in;
    TextView print;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_in = (EditText) findViewById(R.id.name);
        phone_in = (EditText) findViewById(R.id.phone);
        address_in = findViewById(R.id.address);
        type_in = findViewById(R.id.type);
        id_in = (EditText) findViewById(R.id.my_id);
        print = (TextView) findViewById(R.id.print);
        db = new DBHandler(this);
    }

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
}