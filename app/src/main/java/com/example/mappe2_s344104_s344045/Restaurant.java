package com.example.mappe2_s344104_s344045;

public class Restaurant {
    private Long _ID;
    private String name;
    private String address;
    private String phone;
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
}
