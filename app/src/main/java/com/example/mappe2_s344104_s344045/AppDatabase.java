package com.example.mappe2_s344104_s344045;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {Friend.class,
                Restaurant.class,
                Reservation.class},
        version = 3
)

@TypeConverters({RestaurantConverter.class, FriendConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendDao friendDao();
    public abstract RestaurantDao restaurantDao();
    public abstract ReservationDao reservationDao();
}
