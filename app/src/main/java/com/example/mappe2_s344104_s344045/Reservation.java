package com.example.mappe2_s344104_s344045;

import java.util.List;

public class Reservation {
    private long _ID;
    private Restaurant restaurant;
    private String date;
    private String time;
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
