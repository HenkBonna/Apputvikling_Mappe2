package com.example.mappe2_s344104_s344045.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mappe2_s344104_s344045.Models.Friend;
import com.example.mappe2_s344104_s344045.Models.FriendConverter;
import com.example.mappe2_s344104_s344045.Models.FriendDao;
import com.example.mappe2_s344104_s344045.Models.Reservation;
import com.example.mappe2_s344104_s344045.Models.ReservationDao;
import com.example.mappe2_s344104_s344045.Models.Restaurant;
import com.example.mappe2_s344104_s344045.Models.RestaurantConverter;
import com.example.mappe2_s344104_s344045.Models.RestaurantDao;

@Database(
        entities = {Friend.class,
                Restaurant.class,
                Reservation.class},
        version = 4
)

@TypeConverters({RestaurantConverter.class, FriendConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendDao friendDao();
    public abstract RestaurantDao restaurantDao();
    public abstract ReservationDao reservationDao();
}
