package com.example.mappe2_s344104_s344045;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterFriend extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_friend);
    }

    public void saveFriend(View view) {
        //TODO implement method
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastname = findViewById(R.id.editLastName);
        EditText editPhone = findViewById(R.id.editTextPhone);

        String firstname = editFirstName.getText().toString();
        String lastname = editLastname.getText().toString();
        String phone = editPhone.getText().toString();

        Friend friend = new Friend(firstname, lastname, phone);

        //TODO add friend to DB
    }
}
