package com.example.mappe2_s344104_s344045;

public class Restaurant {
    private Long _ID;
    private String name;
    private String phone;

    public Restaurant (){}

    public Restaurant(String navn, String telefon) {
        name = navn;
        phone = telefon;
    }

    public Restaurant(Long _ID, String navn, String telefon) {
        this._ID = _ID;
        name = navn;
        phone = telefon;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
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
}
