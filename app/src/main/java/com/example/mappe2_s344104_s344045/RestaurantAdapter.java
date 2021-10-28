package com.example.mappe2_s344104_s344045;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    Context context;
    private int layoutResourceId;
    private List<Restaurant> restaurantList;
    MenuInflater menuInflater;

    public RestaurantAdapter(Context context, int layoutResourceId, List<Restaurant> restaurantList){
        super(context, layoutResourceId, restaurantList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.restaurantList = restaurantList;
    }

    public static class ViewHolder{
        public TextView textView_restaurantName;
        public TextView textView_restaurantType;
        public TextView textView_restaurantPhoneNumber;
        public TextView textView_restaurantAddress;
        public ImageButton button_editRestaurant;
        public PopupMenu popup;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView_restaurantName = (TextView) row.findViewById(R.id.textView_restaurantName);
            holder.textView_restaurantType = (TextView) row.findViewById(R.id.textView_restaurantType);
            holder.textView_restaurantPhoneNumber = (TextView) row.findViewById(R.id.textView_restaurantPhoneNumber);
            holder.textView_restaurantAddress = (TextView) row.findViewById(R.id.textView_restaurantAddress);
            holder.button_editRestaurant = (ImageButton) row.findViewById(R.id.button_editRestaurant);
            holder.popup = new PopupMenu(getContext(), row);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }
        Restaurant restaurant = restaurantList.get(position);
        holder.textView_restaurantName.setText(restaurant.getName());
        String restaurantTypeString = "(" + restaurant.getType() +")";
        holder.textView_restaurantType.setText(restaurantTypeString);
        holder.textView_restaurantPhoneNumber.setText(restaurant.getPhone());
        holder.textView_restaurantAddress.setText(restaurant.getAddress());
        ViewHolder finalHolder = holder;
        holder.button_editRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuInflater == null){
                    menuInflater = finalHolder.popup.getMenuInflater();
                }
                finalHolder.popup.getMenu().clear();
                menuInflater.inflate(R.menu.list_menu, finalHolder.popup.getMenu());
                finalHolder.popup.show();
                finalHolder.popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit_entry:
                                Intent i = new Intent(context, RegisterRestaurant.class);
                                Bundle bundle = new Bundle();
                                bundle.putLong("restaurant_id", restaurant.get_ID());
                                i.putExtras(bundle);
                                context.startActivity(i);
                                break;
                            case R.id.delete_entry:
                                deleteEntry(restaurant);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        return row;
    }

    private void deleteEntry(Restaurant restaurant) {
        class DeleteEntry extends AsyncTask<Restaurant, Void, Void> {
            @Override
            protected Void doInBackground(Restaurant... r){
                DatabaseClient.getInstance(getContext())
                        .getAppDatabase()
                        .restaurantDao()
                        .delete(restaurant);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                remove(restaurant);
                notifyDataSetChanged();
            }
        }
        DeleteEntry de = new DeleteEntry();
        de.execute();
    }
}