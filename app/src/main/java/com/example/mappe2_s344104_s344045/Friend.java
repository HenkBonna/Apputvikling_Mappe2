package com.example.mappe2_s344104_s344045;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Friend {
    @PrimaryKey(autoGenerate = true)
    private long _ID;
    @ColumnInfo(name = "first_name")
    private String firstname;
    @ColumnInfo(name = "last_name")
    private String lastname;
    @ColumnInfo(name = "phone_number")
    private String phone;

    //We treating our friends like objects here
    public Friend() {
    }

    public Friend(String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Friend(int _ID, String firstname, String lastname, String phone) {
        this._ID = _ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
