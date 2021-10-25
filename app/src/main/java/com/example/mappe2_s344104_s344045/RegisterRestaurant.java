package com.example.mappe2_s344104_s344045;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterRestaurant extends AppCompatActivity {
    EditText editName, editAddress, editPhone, editType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
        editName = findViewById(R.id.restaurant_name);
        editAddress = findViewById(R.id.restaurant_address);
        editPhone = findViewById(R.id.restaurant_phone);
        editType = findViewById(R.id.restaurant_type);
    }

    public void add(View view) {
        class SaveRestaurant extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids){
                String name = editName.getText().toString();
                String address = editAddress.getText().toString();
                String phone = editPhone.getText().toString();
                String type = editType.getText().toString();

                Restaurant restaurant = new Restaurant(name, address, phone, type);
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .restaurantDao()
                        .insert(restaurant);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Restaurant saved",Toast.LENGTH_LONG).show();
                finish();
            }
        }
        SaveRestaurant sr = new SaveRestaurant();
        sr.execute();
    }
}
