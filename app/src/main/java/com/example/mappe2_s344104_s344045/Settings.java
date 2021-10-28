package com.example.mappe2_s344104_s344045;

import android.Manifest;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Settings extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences settings;
    private TimePicker tp;
    private Switch notif, sms;
    private EditText messageTxt;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //getting shared preferences and an editor
        settings = getSharedPreferences(ListReservations.PREFS, MODE_PRIVATE);
        editor = settings.edit();
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //finding timepicker and setting to 24hour view
        tp = findViewById(R.id.timepicker);
        tp.setIs24HourView(true);

        //finding switches for notifications and sms service, setting to correct check
        notif = findViewById(R.id.notif_switch);
        notif.setChecked(settings.getBoolean("notification_enabled", false));
        sms = findViewById(R.id.sms_switch);
        sms.setChecked(settings.getBoolean("sms_enabled", false));

        //finding standard message text field
        messageTxt = findViewById(R.id.messageTextStandard);

        //making sure standard_message exists, and setting it in the text field
        if (!settings.contains("standard_message")){
            Log.e("PREFERENCE ADDED", "Preference added");
            editor.putString("standard_message", "Husk reservasjon i kveld!");
        }
        messageTxt.setText(settings.getString("standard_message", null));

        //onchange listeners to save the new message and time when it is edited
        messageTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                editor.putString("standard_message", editable.toString());
                editor.apply();
            }
        });

        TimePicker.OnTimeChangedListener onTimeChangedListener = new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                editor.putInt("hour", tp.getHour());
                editor.putInt("minute", tp.getMinute());
                editor.apply();
                cancelAlarm();
                sendBroadcast();
            }
        };

        //onchange listeners to save notification and sms service
        CompoundButton.OnCheckedChangeListener onSMSCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (settings.getBoolean("sms_enabled", true)) {
                        editor.putBoolean("sms_enabled", false);
                        cancelAlarm();
                    } else {
                        editor.putBoolean("sms_enabled", true);
                        resumeAlarm();
                    }
                    editor.apply();
                } else {
                    ActivityCompat.requestPermissions(getParent(), new String[]{android.Manifest.permission.SEND_SMS}, 1);
                }
            }


        };
        CompoundButton.OnCheckedChangeListener onNotifCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (settings.getBoolean("notification_enabled", true)){
                    editor.putBoolean("notification_enabled", false);
                } else {
                    editor.putBoolean("notification_enabled", true);
                }
                editor.apply();
            }
        };
        if (!settings.contains("notification_enabled")){
            editor.putBoolean("notification_enabled", false);
        }
        if (!settings.contains("sms_enabled")){
            editor.putBoolean("sms_enabled", false);
        }

        //initiating timepicker to match preference, and setting onchange listeners
        tp.setHour(settings.getInt("hour", 8));
        tp.setMinute(settings.getInt("minute", 0));
        tp.setOnTimeChangedListener(onTimeChangedListener);
        sms.setOnCheckedChangeListener(onSMSCheckedChangeListener);
        notif.setOnCheckedChangeListener(onNotifCheckedChangeListener);
    }
    public void sendBroadcast(){
        Intent intent = new Intent();
        intent.setAction("MY_BROADCAST");
        sendBroadcast(intent);
    }
    private void cancelAlarm() {
        PeriodicService.cancelAlarm();
    }
    private void resumeAlarm(){
        PeriodicService.startAlarm();
    }
}
