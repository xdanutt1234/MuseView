package com.myapp.museview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MuseumDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_museum_details);
        TextView name = findViewById(R.id.textViewName);
        TextView desciption = findViewById(R.id.textViewDetails);
        TextView noreviews = findViewById(R.id.noreviews);
        RatingBar ratingBar = findViewById(R.id.ratingBarDetails);
        Button button_map = findViewById(R.id.button_map);
        Button button_back = findViewById(R.id.buttonBackDetails);
        Button add_review = findViewById(R.id.add_review);
        ImageView image = findViewById(R.id.imageDetails);
        image.setImageResource(getResources().getIdentifier(intent.getStringExtra("image"),"drawable",getPackageName()));
        name.setText(intent.getStringExtra("name"));
        desciption.setText(intent.getStringExtra("description"));
        SessionManager session = new SessionManager(getApplicationContext());
        Log.d("image",intent.getStringExtra("image"));
        RatingResult result = averageRating(performReviewSearch(intent.getIntExtra("id",-1)));
        if(result.count > 0)
        {

            ratingBar.setRating(result.rating);

            noreviews.setText(Float.toString(result.rating)+"/5 ("+Integer.toString(result.count)+" reviews)");
        }
        else
        {
            ratingBar.setRating(0.0f);
        }
        add_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MuseumDetailsActivity.this, ReviewListActivity.class);
                intent1.putExtras(intent.getExtras());
                startActivity(intent1);
            }
        });
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MuseumDetailsActivity.this, MapActivity.class);
                intent1.putExtras(intent.getExtras());
                startActivity(intent1);

            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MuseumDetailsActivity.this, MenuActivity.class);
                startActivity(intent1);
            }
        });

    }
    private List<MuseumReview> performReviewSearch(int idmuseum)
    {   DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        List<MuseumReview> list = new ArrayList<>();

        cursor = db.query(
                "MUSEUMREVIEW",
                new String[]{"idMuseumReview","idUser","idMuseum","rating", "comment"},
                "idMuseum = " + idmuseum,
                null,
                null,
                null,
                null
        );
        list.clear();
        while (cursor.moveToNext())
        {
            list.add(new MuseumReview(cursor.getInt(cursor.getColumnIndexOrThrow("idMuseumReview")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idUser")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idMuseum")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("rating")),
                    cursor.getString(cursor.getColumnIndexOrThrow("comment"))));
        }


        return list;
    }
    private RatingResult averageRating(List<MuseumReview> list)
    {
        RatingResult xa = new RatingResult();
        xa.rating = 0.0f;
        xa.count = 0;
        for(MuseumReview x : list)
        {
            xa.count++;
            xa.rating += x.getRating();
        }
        List<Double> result = new ArrayList<>();
        xa.rating = xa.rating/xa.count;
        return xa;
    }
}
