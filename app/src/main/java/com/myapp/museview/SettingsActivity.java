package com.myapp.museview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SessionManager session = new SessionManager(getApplicationContext());
        Button buttonBack = findViewById(R.id.buttonBackSettings);
        Button logout = findViewById(R.id.log_out_button);
        TextView settingsprompt = findViewById(R.id.textViewWelcomeSettings);
        settingsprompt.setText(session.getUsername() + "'s settings");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.endSession();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
            }
        });

    }
}

