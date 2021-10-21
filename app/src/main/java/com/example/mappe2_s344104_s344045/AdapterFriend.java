package com.example.mappe2_s344104_s344045;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class AdapterFriend<F> extends ArrayAdapter<Friend> {
    private Activity activity;
    private List<Friend> friendList;
    private static LayoutInflater inflater = null;

    public AdapterFriend (Activity activity, int textViewResourdeId, List<Friend> _friendList){
        super(activity, textViewResourdeId, _friendList);
        try {
            this.activity = activity;
            this.friendList = _friendList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {

        }
    }
}