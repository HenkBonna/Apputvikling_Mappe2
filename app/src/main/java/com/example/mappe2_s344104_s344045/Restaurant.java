package com.example.mappe2_s344104_s344045;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    private Long _ID;
    @ColumnInfo(name = "restaurant_name")
    private String name;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "restaurant_phone")
    private String phone;
    @ColumnInfo(name = "restaurant_type")
    private String type;

    public Restaurant (){}

    public Restaurant(String name, String address, String phone, String type) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    public Restaurant(Long _ID, String name, String address, String phone, String type) {
        this._ID = _ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    @Override
    public String toString(){
        return name + ", " + address + ", " + type;
    }
}
