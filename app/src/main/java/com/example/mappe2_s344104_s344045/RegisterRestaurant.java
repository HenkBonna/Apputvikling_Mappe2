package com.example.mappe2_s344104_s344045;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterRestaurant extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
    }

    public void add(View view) {
        //TODO implement method
        EditText editName = findViewById(R.id.restaurant_name);
        EditText editAddress = findViewById(R.id.restaurant_address);
        EditText editPhone = findViewById(R.id.restaurant_phone);
        EditText editType = findViewById(R.id.restaurant_type);

        String name = editName.getText().toString();
        String address = editAddress.getText().toString();
        String phone = editPhone.getText().toString();
        String type = editType.getText().toString();

        Restaurant restaurant = new Restaurant(name, address, phone, type);

        //TODO save restaurant in DB
    }
}
