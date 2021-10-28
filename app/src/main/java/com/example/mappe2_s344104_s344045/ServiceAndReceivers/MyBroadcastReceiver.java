package com.example.mappe2_s344104_s344045.ServiceAndReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        context.stopService(new Intent(context, PeriodicService.class));
        context.startService(new Intent(context, PeriodicService.class));
    }
}
