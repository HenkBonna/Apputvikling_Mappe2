package com.example.mappe2_s344104_s344045;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Settings extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences settings;
    private TimePicker tp;
    private Switch notif, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        tp = findViewById(R.id.timepicker);
        tp.setIs24HourView(true);
        settings = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        editor = settings.edit();
        notif = findViewById(R.id.notif_switch);
        sms = findViewById(R.id.sms_switch);

        sms.setChecked(settings.getBoolean("sms_enabled", false));
        notif.setChecked(settings.getBoolean("notification_enabled", false));

        TimePicker.OnTimeChangedListener onTimeChangedListener = new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                Log.d("TAG", "Time changed");
                editor.putInt("hour", tp.getHour());
                editor.putInt("minute", tp.getMinute());
                editor.apply();
            }
        };
        CompoundButton.OnCheckedChangeListener onSMSCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (settings.getBoolean("sms_enabled", true)){
                    editor.putBoolean("sms_enabled", false);
                } else {
                    editor.putBoolean("sms_enabled", true);
                }
                editor.apply();
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

        tp.setHour(settings.getInt("hour", 8));
        tp.setMinute(settings.getInt("minute", 0));
        tp.setOnTimeChangedListener(onTimeChangedListener);
        sms.setOnCheckedChangeListener(onSMSCheckedChangeListener);
        notif.setOnCheckedChangeListener(onNotifCheckedChangeListener);
    }
}
