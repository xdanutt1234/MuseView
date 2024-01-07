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
