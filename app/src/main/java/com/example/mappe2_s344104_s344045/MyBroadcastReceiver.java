package com.example.mappe2_s344104_s344045;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Log.e("TAG", "Broadcast received");
        context.stopService(new Intent(context, PeriodicService.class));
        context.startService(new Intent(context, PeriodicService.class));
    }

    public MyBroadcastReceiver(){
        Log.e("BROADCASTRECEIVER", "BroadcastReceiver constructor called");
    }
}
