/**
 * Activitatea pentru verificarea codului de confirmare și înregistrarea unui utilizator.
 * Această activitate gestionează procesul de verificare a codului trimis prin email și adaugarea utilizatorului în baza de date.
 */
package com.myapp.museview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class VerifyActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        intent = getIntent();
        Button button = findViewById(R.id.buttonVerify);
        EditText editText = findViewById(R.id.editTextCode);
        ContentValues values = new ContentValues();
        values.put("Username", intent.getStringExtra("User"));
        values.put("Email", intent.getStringExtra("Email"));
        values.put("Password", intent.getStringExtra("Password"));
        int code = new Random().nextInt(9000) + 1000;
        thread.start();
        Log.d("Extras", intent.getStringExtra("User") + " "+ intent.getStringExtra("Email") + " " +intent.getStringExtra("Password") + " " + intent.getStringExtra("Code"));
        //SendMail.sendEmail("museview6@gmail.com", "shkf hyjb yfed msgs", intent.getStringExtra("Email"), "MuseView verification code", intent.getStringExtra("Code"));
        new SendMail().execute("museview6@gmail.com", "shkfhyjbyfedmsgs", intent.getStringExtra("Email"), "MuseView verification code", Integer.toString(code));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(Integer.toString(code)))
                {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    DatabaseHelper databaseHelper = new DatabaseHelper(VerifyActivity.this);
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    db.insert("User", null, values);
                    Intent intent = new Intent(VerifyActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Log.d("Entered", "Thread");
                Log.d("Intents", intent.getStringExtra("Code") + " " + intent.getStringExtra("Email"));

                //SendMail.sendEmail("museview6@gmail.com", "rlxw ilny rgyr gcub", intent.getStringExtra("Email"),
                //        "MuseView verification code", intent.getStringExtra("Code"));

            } catch (Exception e) {
                Log.e("ThreadException", "Exception in thread", e);
            }
        }
    });
}

