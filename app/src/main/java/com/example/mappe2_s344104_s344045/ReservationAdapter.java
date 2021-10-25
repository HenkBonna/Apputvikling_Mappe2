package com.example.mappe2_s344104_s344045;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
    private Context context;
    private int layoutResourceId;
    private List<Reservation> reservations;

    public ReservationAdapter(Context context, int layoutResourceId, List<Reservation> reservations){
        super(context, layoutResourceId, reservations);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.reservations = reservations;
    }

    public static class ViewHolder{
        public TextView textView_Restaurant;
        public TextView textView_Date;
        public TextView textView_Time;
        public ImageButton button_editReservation;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView_Restaurant = (TextView) row.findViewById(R.id.textView_restaurant);
            holder.textView_Date = (TextView) row.findViewById(R.id.textView_date);
            holder.textView_Time = (TextView) row.findViewById(R.id.textView_time);
            holder.button_editReservation = (ImageButton) row.findViewById(R.id.button_editReservation);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Reservation reservation = reservations.get(position);
        holder.textView_Restaurant.setText(reservation.getRestaurant().getName());
        holder.textView_Date.setText(reservation.getDate());
        holder.textView_Time.setText(reservation.getTime());
        holder.button_editReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO edit reservation
            }
        });

        return row;
    }
}
