package com.example.mappe2_s344104_s344045;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterFriend extends AppCompatActivity {
    EditText editFirstName, editLastName, editPhone;
    TextView headline;
    Friend friend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_friend);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editPhone = findViewById(R.id.editTextPhone);
        headline = findViewById(R.id.addFriendTitle);
        if (getIntent().getExtras() != null){
            headline.setText("Rediger venn");
            Long id = getIntent().getExtras().getLong("friend_id");
            getFriend(id);
        }
    }

    private void getFriend(Long id) {
        class GetFriend extends AsyncTask<Long, Void, Void>{
            @Override
            public Void doInBackground(Long... longs){
                friend = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .friendDao()
                        .get(id);
                return null;
            }
            @Override
            public void onPostExecute(Void aVoid){
                editFirstName.setText(friend.getFirstname());
                editLastName.setText(friend.getLastname());
                editPhone.setText(friend.getPhone());
            }
        }
        GetFriend getFriend = new GetFriend();
        getFriend.execute();
    }

    public void saveFriend(View view) {
        class SaveFriend extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids){
                String firstname = editFirstName.getText().toString();
                String lastname = editLastName.getText().toString();
                String phone = editPhone.getText().toString();
                if (friend != null){
                    friend.setFirstname(firstname);
                    friend.setLastname(lastname);
                    friend.setPhone(phone);
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .friendDao()
                            .update(friend);
                } else {
                    friend = new Friend(firstname, lastname, phone);
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .friendDao()
                            .insert(friend);
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                finish();
            }
        }
        SaveFriend sf = new SaveFriend();
        sf.execute();
    }
}
