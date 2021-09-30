package com.example.mappe2_s344104_s344045;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public void leggTilRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getName());
        values.put(KEY_PH_NO, restaurant.getPhone());
        db.insert(TABLE_RESTAURANTS, null, values);
        db.close();
    }

    public List<Restaurant> finnAlleRestauranter(){
        List<Restaurant> restaurantListe= new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.set_ID(cursor.getLong(0));
                restaurant.setName(cursor.getString(1));
                restaurant.setPhone(cursor.getString(2));
                restaurantListe.add(restaurant);
            }
            while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return restaurantListe;
    }

    public void slettRestaurant(Long inn_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTS, KEY_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }

    public int oppdaterRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getName());
        values.put(KEY_PH_NO, restaurant.getPhone());
        int endret = db.update(TABLE_RESTAURANTS, values, KEY_ID + "= ?", new String[] {String.valueOf(restaurant.get_ID())});
        db.close();
        return endret;
    }

    public int finnAntallRestauranter(){
        String sql = "SELECT * FROM " + TABLE_RESTAURANTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int antall = cursor.getCount();
        cursor.close();
        db.close();
        return antall;
    }
    // finnVerktøy
    public Restaurant finnRestaurant(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANTS, new String[]{
                        KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null) cursor.moveToFirst();
        Restaurant restaurant = new Restaurant(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return restaurant;
    }
}
