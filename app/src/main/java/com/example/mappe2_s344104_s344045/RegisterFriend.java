package com.example.mappe2_s344104_s344045;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterFriend extends AppCompatActivity {
    EditText editFirstName, editLastName, editPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_friend);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editPhone = findViewById(R.id.editTextPhone);
    }

    public void saveFriend(View view) {
        class SaveFriend extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids){
                String firstname = editFirstName.getText().toString();
                String lastname = editLastName.getText().toString();
                String phone = editPhone.getText().toString();
                Friend friend = new Friend(firstname, lastname, phone);
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .friendDao()
                        .insert(friend);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Friend saved",Toast.LENGTH_LONG).show();
                finish();
            }
        }
        SaveFriend sf = new SaveFriend();
        sf.execute();
    }
}
