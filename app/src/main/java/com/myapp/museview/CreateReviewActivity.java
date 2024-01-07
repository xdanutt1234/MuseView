/**
 * Aceasta activitate permite utilizatorilor sa creeze si sa submita reviewuri pentru un muzeu.
 * Include elemente de UI cum ar fi RatingBar pentru rating si EditText pentru a adauga comentarii.
 * Review-ul utilizatorului, impreuna cu ID-ul sau si ID-ul muzeului sunt stocate in baza de date.
 * Dupa submitere utilizatorul este redirectionat catre MuseumDetailsActivity
 * @author Vladu Marian-Dumitru
 * @version 1.0
 */

package com.myapp.museview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class CreateReviewActivity extends AppCompatActivity {

    /**
     * Metoda chemata cand activitatea este creata. Responsabila pentru initializarea activitatii.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_review_activity);
        SessionManager session = new SessionManager(getApplicationContext());
        Intent intent = getIntent();
        String userID = Integer.toString(session.getUserID());
        String museumID = Integer.toString(intent.getIntExtra("id",-1));
        Button addReview = findViewById(R.id.buttonSubmitAdd);
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(CreateReviewActivity.this);
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                String rating;
                String comment;
                RatingBar ratingBar = findViewById(R.id.ratingBarAdd);
                EditText commentText = findViewById(R.id.editTextComment);
                int ratingint = (int)ratingBar.getRating();
                rating = Integer.toString(ratingint);
                comment = commentText.getText().toString();


                ContentValues values = new ContentValues();

                values.put("idUser", userID);
                values.put("idMuseum", museumID);
                values.put("rating", rating);
                values.put("comment", comment);
                db.insert("MuseumReview", null, values);
                db.close();
                databaseHelper.close();
                Intent intent1 = new Intent(CreateReviewActivity.this, MuseumDetailsActivity.class);
                intent1.putExtras(intent.getExtras());
                startActivity(intent1);
            }
        });
    }
}
