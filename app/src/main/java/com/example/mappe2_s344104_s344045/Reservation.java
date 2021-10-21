package com.example.mappe2_s344104_s344045;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    private long _ID;

    @ColumnInfo(name = "restaurant")
    @TypeConverters(RestaurantConverter.class)
    private Restaurant restaurant;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "friends")
    @TypeConverters(FriendConverter.class)
    private FriendsList friends;

    public Reservation() {
    }

    public Reservation(Restaurant restaurant, String date, String time, FriendsList friends) {
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
        this.friends = friends;
    }

    public Reservation(long _ID, Restaurant restaurant, String date, String time, FriendsList friends) {
        this._ID = _ID;
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
        this.friends = friends;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public FriendsList getFriends() {
        return friends;
    }

    public void setFriends(FriendsList friends) {
        this.friends = friends;
    }

    @Override
    public String toString(){
        String str = "Restaurant: " + restaurant + " Dato: " + date + " Tidspunkt: " + time + " Venner: ";
        List<Friend> friendList = friends.getFriends();
        for (Friend f : friendList){
            if (friendList.indexOf(f) +1 < friendList.size()) {
                str += f.toString() + ", ";
            } else {
                str += f.toString();
            }
        }
        return str;
    }
}
