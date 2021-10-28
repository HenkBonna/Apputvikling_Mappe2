package com.example.mappe2_s344104_s344045.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mappe2_s344104_s344045.Models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_RESTAURANTS = "Restauranter";
    static String KEY_ID = "_ID";
    static String KEY_NAME = "Navn";
    static String KEY_ADDRESS = "Adresse";
    static String KEY_PH_NO = "Telefon";
    static String KEY_TYPE = "Type";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Telefonrestauranter";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String LAG_TABELL = "CREATE TABLE " + TABLE_RESTAURANTS + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT," +  KEY_PH_NO + " TEXT," + KEY_TYPE + " TEXT" +")";
        Log.d("SQL", LAG_TABELL);
        db.execSQL(LAG_TABELL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        onCreate(db);
    }
}
