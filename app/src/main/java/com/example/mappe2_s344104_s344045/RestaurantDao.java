package com.example.mappe2_s344104_s344045;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("Select * FROM Restaurant")
    List<Restaurant> getAll();
    @Query("SELECT * from Restaurant where _ID =:id")
    Restaurant get(Long id);
    @Insert
    void insert (Restaurant restaurant);
    @Delete
    void delete(Restaurant restaurant);
    @Update
    void update(Restaurant restaurant);
}
