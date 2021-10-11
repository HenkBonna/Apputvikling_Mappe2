package com.example.mappe2_s344104_s344045;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    private long _ID;
    @ColumnInfo(name = "restaurant")
    private Restaurant restaurant;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "friends")
    private List<Friend> friends;

    public Reservation() {
    }

    public Reservation(Restaurant restaurant, String date, String time, List<Friend> friends) {
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
        this.friends = friends;
    }

    public Reservation(long _ID, Restaurant restaurant, String date, String time, List<Friend> friends) {
        this._ID = _ID;
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
        this.friends = friends;
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

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
