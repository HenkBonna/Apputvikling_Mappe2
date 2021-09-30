package com.example.mappe2_s344104_s344045;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Restaurant {
    private Long _ID;
    private String Navn;
    private String Telefon;

    public Restaurant (){}

    public Restaurant(String navn, String telefon) {
        Navn = navn;
        Telefon = telefon;
    }

    public Restaurant(Long _ID, String navn, String telefon) {
        this._ID = _ID;
        Navn = navn;
        Telefon = telefon;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }
}
