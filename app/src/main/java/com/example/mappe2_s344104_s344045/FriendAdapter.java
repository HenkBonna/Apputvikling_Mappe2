package com.example.mappe2_s344104_s344045;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import com.example.mappe2_s344104_s344045.Friend;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FriendAdapter extends ArrayAdapter<Friend> {
    Context context;
    private int layoutResourceId;
    private List<Friend> friendList;
    private List<Friend> checkedFriends;

    public FriendAdapter(Context context, int layoutResourceId, List<Friend> friendList){
        super(context, layoutResourceId, friendList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.friendList = friendList;
        checkedFriends = new ArrayList<>();
    }

    public static class ViewHolder{
        public TextView textView_firstName;
        public TextView textView_lastName;
        public TextView textView_phoneNumber;
        public ImageButton button_edit;
        public CheckBox checkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView_firstName = (TextView) row.findViewById(R.id.textView_firstName);
            holder.textView_lastName = (TextView) row.findViewById(R.id.textView_lastName);
            holder.textView_phoneNumber = (TextView) row.findViewById(R.id.textView_phoneNumber);
            if (layoutResourceId == R.layout.friend_entry) {
                holder.button_edit = (ImageButton) row.findViewById(R.id.button_edit);
            } else {
                holder.checkBox = (CheckBox) row.findViewById(R.id.checkBox);
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        int getPosition = (Integer) compoundButton.getTag();
                        friendList.get(getPosition).setChecked(compoundButton.isChecked());
                    }
                });
            }
            row.setTag(holder);
            row.setTag(R.id.checkBox, holder.checkBox);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }
        Friend friend = friendList.get(position);
        holder.textView_firstName.setText(friend.getFirstname());
        holder.textView_lastName.setText(friend.getLastname());
        String friendPhoneString = "(" + friend.getPhone() + ")";
        holder.textView_phoneNumber.setText(friendPhoneString);
        if (layoutResourceId == R.layout.friend_entry) {
            holder.button_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "YOU CLICKED EDIT ON FRIEND WITH ID: " + friend.get_ID() + " ! ", Toast.LENGTH_LONG).show(); //TODO: This
                }
            });
        } else if (layoutResourceId == R.layout.friend_picker){
            //int getPosition = (Integer) row.getTag();
            holder.checkBox.setTag(position);
            holder.checkBox.setChecked(friendList.get(position).getChecked());
        }

        return row;
    }

    public List<Friend> getCheckedFriends() {
        for (Friend f : friendList){
            if (f.getChecked()){
                checkedFriends.add(f);
                f.setChecked(false);
            }
        }
        return checkedFriends;
    }
}