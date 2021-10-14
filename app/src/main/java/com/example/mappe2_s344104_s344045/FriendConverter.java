package com.example.mappe2_s344104_s344045;

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
            Long id = Long.parseLong(friendData.get(0));
            String fName = friendData.get(1), lName = friendData.get(2), phone = friendData.get(3);
            friendList.add(new Friend(id, fName, lName, phone));
        }
        return new FriendsList(friendList);
    }

    @TypeConverter
    public String stringFromFriendsList(FriendsList friendsList){
        return friendsList.toString();
    }
}
