/**
 * LoginActivity este o activitate care gestionează procesul de autentificare în aplicație.
 * Utilizatorii pot introduce numele de utilizator și parola, iar apoi pot încerca să se autentifice.
 * Dacă autentificarea este reușită, utilizatorul este redirecționat către activitatea MenuActivity.
 * În plus, utilizatorii au opțiunea de a se înscrie (RegisterActivity) sau de a reveni la ecranul principal (MainActivity).
 *
 * Utilizare:
 * Pentru a utiliza această activitate, trebuie să o porniți prin intermediul unei intentii.
 * Exemplu:
 * Intent intentie = new Intent(LoginActivity.this, LoginActivity.class);
 * startActivity(intentie);
 *
 * @author Vladu Marian-Dumitru
 * @version 1.0
 */
package com.myapp.museview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buton_back = (Button) findViewById(R.id.buttonBack);
        final Button buton_login = findViewById(R.id.buttonLogin);
        final TextView buton_register = (TextView) findViewById(R.id.textViewInregistrareLogin);
        final View transitionView = (View) findViewById(R.id.transitionSquareView);
        /*  final TextView usernameEditText = (TextView)findViewById(R.id.editTextUsername); */

        buton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransitionAnimation(transitionView, new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        buton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userEdit = findViewById(R.id.editTextUsername);
                EditText passwordEdit = findViewById(R.id.editTextPassword);
                String user = userEdit.getText().toString();
                String pass = passwordEdit.getText().toString();

                if (checkLogin(user, pass)) {
                    SessionManager sesiune = new SessionManager(getApplicationContext());
                    sesiune.startSession(getUserIDByUsername(user), user);
                    startTransitionAnimation(transitionView, new Intent(LoginActivity.this, MenuActivity.class));
                }



            }
        });
        buton_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTransitionAnimation(transitionView, new Intent(LoginActivity.this, RegisterActivity.class));
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

    /**
     * Verifică dacă un utilizator este autentificat în baza de date.
     *
     * @param username Numele de utilizator introdus.
     * @param password Parola introdusă.
     * @return true dacă autentificarea este reușită, false în caz contrar.
     */
    public boolean checkLogin(String username, String password) {

        String[] projection = {"id"};
        String[] selectionArgs = {username, password};
        String selection = "Username = ? AND Password = ?";
        DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("User", projection, selection, selectionArgs, null, null, null);

        return cursor.getCount() > 0;
    }

    /**
     * Obține ID-ul utilizatorului pe baza numelui de utilizator.
     *
     * @param username Numele de utilizator pentru care se dorește obținerea ID-ului.
     * @return ID-ul utilizatorului sau -1 dacă nu este găsit.
     */
    public int getUserIDByUsername(String username)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = null;

        String[] projection = {"id"};
        String[] selectionArgs = {username};
        String selection = "Username = ?";
        cursor = db.query("User", projection, selection, selectionArgs, null, null, null);
        int coloana = cursor.getColumnIndex("id");
        if (cursor != null && cursor.moveToFirst()) {

            return cursor.getInt(coloana);
        } else {

            return -1;
        }

    }
}


