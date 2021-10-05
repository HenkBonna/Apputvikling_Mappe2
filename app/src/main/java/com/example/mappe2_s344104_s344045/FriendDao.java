package com.example.mappe2_s344104_s344045;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FriendDao {
    @Query("Select * from Friend")
    List<Friend> getAll();
    @Insert
    void insert(Friend friend);
    @Delete
    void delete(Friend friend);
    @Update
    void update(Friend friend);
}
