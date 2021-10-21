package com.example.mappe2_s344104_s344045;


import java.util.ArrayList;
import java.util.List;

public class FriendsList {
    private ArrayList<Friend> friends;

    public FriendsList() {
        friends = new ArrayList<>();
    }

    public FriendsList(List<Friend> friends) {
        this.friends = (ArrayList<Friend>) friends;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = (ArrayList<Friend>) friends;
    }

    public void add(Friend friend){
        friends.add(friend);
    }

    @Override
    public String toString() {
        String s = "";
        for (Friend f : friends){
            s += "" + f.get_ID() + " " + f.getFirstname() + " " + f.getLastname() + " " + f.getPhone() + ", ";
        }
        return s;
    }
}
