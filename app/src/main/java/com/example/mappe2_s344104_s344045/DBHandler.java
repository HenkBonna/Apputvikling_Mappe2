package com.example.mappe2_s344104_s344045;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    static String TABLE_RESTAURANTER = "Restauranter";
    static String KEY_ID = "_ID";
    static String KEY_NAME = "Navn";
    static String KEY_PH_NO = "Telefon";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Telefonrestauranter";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String LAG_TABELL = "CREATE TABLE " + TABLE_RESTAURANTER + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        Log.d("SQL", LAG_TABELL);
        db.execSQL(LAG_TABELL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTER);
        onCreate(db);
    }

    public void leggTilRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getNavn());
        values.put(KEY_PH_NO, restaurant.getTelefon());
        db.insert(TABLE_RESTAURANTER, null, values);
        db.close();
    }

    public List<Restaurant> finnAlleRestauranter(){
        List<Restaurant> restaurantListe= new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.set_ID(cursor.getLong(0));
                restaurant.setNavn(cursor.getString(1));
                restaurant.setTelefon(cursor.getString(2));
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
        db.delete(TABLE_RESTAURANTER, KEY_ID + " =? ", new String[]{Long.toString(inn_id)});
        db.close();
    }

    public int oppdaterRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getNavn());
        values.put(KEY_PH_NO, restaurant.getTelefon());
        int endret = db.update(TABLE_RESTAURANTER, values, KEY_ID + "= ?", new String[] {String.valueOf(restaurant.get_ID())});
        db.close();
        return endret;
    }

    public int finnAntallRestauranter(){
        String sql = "SELECT * FROM " + TABLE_RESTAURANTER;
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
        Cursor cursor = db.query(TABLE_RESTAURANTER, new String[]{
                        KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null) cursor.moveToFirst();
        Restaurant restaurant = new Restaurant(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return restaurant;
    }
}
