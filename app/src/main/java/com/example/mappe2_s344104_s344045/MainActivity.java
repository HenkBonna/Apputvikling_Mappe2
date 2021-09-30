package com.example.mappe2_s344104_s344045;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText navninn;
    EditText telefoninn;
    EditText idinn;
    TextView utskrift;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navninn = (EditText) findViewById(R.id.navn);
        telefoninn = (EditText) findViewById(R.id.telefon);
        idinn = (EditText) findViewById(R.id.min_id);
        utskrift = (TextView) findViewById(R.id.utskrift);
        db = new DBHandler(this);
    }

    public void leggtil(View v){
        Restaurant restaurant = new Restaurant (navninn.getText().toString(), telefoninn.getText().toString());
        db.leggTilRestaurant(restaurant);
        Log.d("Legg inn: ", "legger til restauranter");

    }
    public void visalle(View v){
        String tekst = "";
        List<Restaurant> restauranter = db.finnAlleRestauranter();

        for (Restaurant restaurant : restauranter){
            tekst = tekst + "Id: " + restaurant.get_ID() + ",Navn: "
                    + restaurant.getNavn() + ",Telefon: " +restaurant.getTelefon();
            Log.d("Navn: ", tekst);
        }
        utskrift.setText(tekst);

    }
    public void slett(View v){
        Long restaurantid = (Long.parseLong(idinn.getText().toString()));
        db.slettRestaurant(restaurantid);
    }
    public void oppdater(View v){
        Restaurant restaurant = new Restaurant();
        restaurant.setNavn(navninn.getText().toString());
        restaurant.setTelefon(telefoninn.getText().toString());
        restaurant.set_ID(Long.parseLong(idinn.getText().toString()));
        db.oppdaterRestaurant(restaurant);
    }
}