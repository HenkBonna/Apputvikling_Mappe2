package com.example.mappe2_s344104_s344045.Models;

import android.util.Log;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendConverter {
    @TypeConverter
    public FriendsList friendsListFromString(String friends){
        List<String> friendStrings = Arrays.asList(friends.split("\\s*,\\s*"));
        List<Friend> friendList = new ArrayList<>();
        for (String s : friendStrings){
            List<String> friendData = Arrays.asList(s.split("\\s+"));
            Long id;
            try {
                id = Long.parseLong(friendData.get(0));
            } catch (Exception e){
                Log.e("ERROR", "Conversion error");
                id = null;
            }
            if (id != null) {
                String fName = friendData.get(1), lName, phone;
                for (int i = 2; i < friendData.size() -2; i++){
                        fName += " " +friendData.get(i);
                }
                lName = friendData.get(friendData.size()-2);
                phone = friendData.get(friendData.size()-1);
                friendList.add(new Friend(id, fName, lName, phone));
            }
        }
        return new FriendsList(friendList);
    }

    @TypeConverter
    public String stringFromFriendsList(FriendsList friendsList){
        return friendsList.toString();
    }
}
