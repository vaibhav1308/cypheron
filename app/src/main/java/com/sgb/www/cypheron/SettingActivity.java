package com.sgb.www.cypheron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sgb.www.cypheron.ui.main.SaveState;

public class SettingActivity extends AppCompatActivity {

    SaveState saveState;
    SwitchCompat dark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saveState = new SaveState(this);
        if(saveState.getState() == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_setting);

        Button reset = findViewById(R.id.RestartButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveState.setState(false);
                dark.setChecked(false);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                finish();
                startActivity(getIntent());
            }
        });

        dark = findViewById(R.id.DarkSwitch);

        if(saveState.getState() == true){
            dark.setChecked(true);
        }else {
            dark.setChecked(false);
        }

        dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    saveState.setState(true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    saveState.setState(false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }
}