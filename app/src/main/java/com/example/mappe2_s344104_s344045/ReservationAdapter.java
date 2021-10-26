package com.example.mappe2_s344104_s344045;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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
        public PopupMenu popup;
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
            holder.popup = new PopupMenu(getContext(), row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        Reservation reservation = reservations.get(position);
        holder.textView_Restaurant.setText(reservation.getRestaurant().getName());
        holder.textView_Date.setText(reservation.getDate());
        holder.textView_Time.setText(reservation.getTime());
        ViewHolder finalHolder = holder;
        holder.button_editReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuInflater menuInflater = finalHolder.popup.getMenuInflater();
                menuInflater.inflate(R.menu.list_menu, finalHolder.popup.getMenu());
                finalHolder.popup.show();
                finalHolder.popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit_entry:
                                Intent i = new Intent(context, AddReservation.class);
                                Bundle bundle = new Bundle();
                                bundle.putLong("reservation_ID", reservation.get_ID());
                                i.putExtras(bundle);
                                context.startActivity(i);
                                break;
                            case R.id.delete_entry:
                                deleteEntry(reservation);
                                break;
                        }
                        return false;
                    }
                });
            }
        });

        return row;
    }

    private void deleteEntry(Reservation reservation) {
        class DeleteEntry extends AsyncTask<Reservation, Void, Void>{
            @Override
            protected Void doInBackground(Reservation... r){
                DatabaseClient.getInstance(getContext())
                        .getAppDatabase()
                        .reservationDao()
                        .delete(reservation);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                remove(reservation);
                notifyDataSetChanged();
            }
        }
        DeleteEntry de = new DeleteEntry();
        de.execute();
    }
}
