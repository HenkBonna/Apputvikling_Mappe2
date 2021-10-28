package com.example.mappe2_s344104_s344045.Models;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class RestaurantConverter {
    @TypeConverter
    public static Restaurant restaurantFromString(String restaurantString){
        List<String> resData = Arrays.asList(restaurantString.split("\\s*,\\s*"));
        long id = Long.parseLong(resData.get(0));
        String name = resData.get(1);
        String address = resData.get(2);
        String phone = resData.get(3);
        String type = resData.get(4);
        return new Restaurant(id, name, address, phone, type);
    }

    @TypeConverter
    public static String stringFromRestaurant(Restaurant restaurant){
        String id = "" + restaurant.get_ID(), name = restaurant.getName(),
        address = restaurant.getAddress(), phone = restaurant.getPhone(),
        type = restaurant.getType();
        return id + "," + name + "," + address + "," + phone + "," + type;
    }
}
