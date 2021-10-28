package com.example.mappe2_s344104_s344045.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReservationDao {
    @Query("Select * from Reservation")
    List<Reservation> getAll();
    @Query("Select * From Reservation where _ID =:id")
    Reservation get(Long id);
    @Insert
    void insert(Reservation r);
    @Delete
    void delete(Reservation r);
    @Update
    void update(Reservation r);
}
