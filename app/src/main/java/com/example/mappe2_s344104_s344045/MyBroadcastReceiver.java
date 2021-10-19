package com.example.mappe2_s344104_s344045;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Broadcast received", Toast.LENGTH_SHORT).show();
        context.stopService(new Intent(context, PeriodicService.class));
        context.startService(new Intent(context, PeriodicService.class));
    }
}
