package com.example.mappe2_s344104_s344045;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Friend.class, Restaurant.class, Reservation.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendDao friendDao();
    public abstract RestaurantDao restaurantDao();
    public abstract ReservationDao reservationDao();
}
