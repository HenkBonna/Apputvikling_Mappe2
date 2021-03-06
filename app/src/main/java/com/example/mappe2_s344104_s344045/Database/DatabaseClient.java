package com.example.mappe2_s344104_s344045.Database;

import android.content.Context;

import androidx.room.Room;

import java.io.File;

public class DatabaseClient {
    private Context mCtx;
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx){
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx.getApplicationContext(), AppDatabase.class, "MyFriends")
                .fallbackToDestructiveMigration()
                .createFromFile(new File("mypath"))
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
