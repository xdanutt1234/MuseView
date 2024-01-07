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

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class RegisterActivity extends AppCompatActivity {
    String username, passwordemail, to, subject, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button buton_back = findViewById(R.id.buttonBackRegister);
        final Button buton_register = findViewById(R.id.buttonRegister);
        final View transitionView = findViewById(R.id.transitionSquareViewRegister);
        /*  final TextView usernameEditText = (TextView)findViewById(R.id.editTextUsername); */


        buton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransitionAnimation(transitionView, new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        buton_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEdit = findViewById(R.id.editTextEmailRegister);
                EditText userEdit = findViewById(R.id.editTextUsernameRegister);
                EditText passwordEdit = findViewById(R.id.editTextPasswordRegister);
                String email = emailEdit.getText().toString();
                String user = userEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(RegisterActivity.this);
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues valori = new ContentValues();
                if (user.length() > 5 && password.length() > 5 && emailCheck(email)) {
                    //valori.put("Username", user);
                    //valori.put("Email", email);
                    //valori.put("Password", password);
                    int code = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
                    username = "museview6@gmail.com";
                    passwordemail = "shkf hyjb yfed msgs";
                    to = email;
                    subject = "MuseView verification code";
                    body = Integer.toString(code);

                    databaseHelper.close();
                    Intent intent = new Intent(RegisterActivity.this, VerifyActivity.class);
                    intent.putExtra("User", user);
                    intent.putExtra("Email", email);
                    intent.putExtra("Password", password);
                    intent.putExtra("Code", Integer.toString(code));
                    //db.insert("User", null, valori);

                    startTransitionAnimation(transitionView, intent);
                }
                databaseHelper.close();
            }
        });

    }

    private void startTransitionAnimation(final View transitionView, android.content.Intent intentie) {
        transitionView.setVisibility(View.VISIBLE);
        transitionView.setTranslationY(-transitionView.getHeight());
        transitionView.animate()
                .translationY(0)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intentie);
                    }
                })
                .start();
    }

    public boolean emailCheck(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._]{1,16}+@{1}+[a-z]{1,7}\\.[a-z]{1,3}$");
        Matcher mail = pattern.matcher(email);
        if (mail.find())
            return true;
        return false;
    }
}
