package com.example.mappe2_s344104_s344045;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterRestaurant extends AppCompatActivity {
    EditText editName, editAddress, editPhone, editType;
    TextView headline;
    Restaurant restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
        editName = findViewById(R.id.restaurant_name);
        editAddress = findViewById(R.id.restaurant_address);
        editPhone = findViewById(R.id.restaurant_phone);
        editType = findViewById(R.id.restaurant_type);
        headline = findViewById(R.id.add_restaurant_title);
        if (getIntent().getExtras() != null){
            headline.setText("Rediger restaurant");
            Long id = getIntent().getExtras().getLong("restaurant_id");
            getRestaurant(id);
        }
    }

    private void getRestaurant(Long id) {
        class GetRestaurant extends AsyncTask<Long, Void, Void>{
            @Override
            public Void doInBackground(Long... longs){
                restaurant = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .restaurantDao()
                        .get(id);
                return null;
            }
            @Override
            public void onPostExecute(Void aVoid){
                editName.setText(restaurant.getName());
                editAddress.setText(restaurant.getAddress());
                editPhone.setText(restaurant.getPhone());
                editType.setText(restaurant.getType());
            }
        }
        GetRestaurant gr = new GetRestaurant();
        gr.execute();
    }

    public void add(View view) {
        class SaveRestaurant extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids){
                String name = editName.getText().toString();
                String address = editAddress.getText().toString();
                String phone = editPhone.getText().toString();
                String type = editType.getText().toString();

                if (restaurant != null){
                    restaurant.setName(name);
                    restaurant.setAddress(address);
                    restaurant.setPhone(phone);
                    restaurant.setType(type);
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .restaurantDao()
                            .update(restaurant);
                } else {
                    restaurant = new Restaurant(name, address, phone, type);
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .restaurantDao()
                            .insert(restaurant);
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                finish();
            }
        }
        SaveRestaurant sr = new SaveRestaurant();
        sr.execute();
    }
}
