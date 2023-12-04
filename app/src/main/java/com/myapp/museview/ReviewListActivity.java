package com.myapp.museview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_reviewlist);
        List<MuseumReview> list = performReviewSearch(intent.getIntExtra("id",-1));
        RecyclerView recyclerView = findViewById(R.id.recyclerViewResults);
        ReviewAdapter adapter = new ReviewAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button button_add = findViewById(R.id.add_review_create);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReviewListActivity.this, CreateReviewActivity.class);
                intent1.putExtras(intent.getExtras());
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
            String name = getName(cursor.getInt(cursor.getColumnIndexOrThrow("idUser")));
            Log.d("Name2",name);
            list.add(new MuseumReview(cursor.getInt(cursor.getColumnIndexOrThrow("idMuseumReview")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idUser")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idMuseum")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("rating")),
                    cursor.getString(cursor.getColumnIndexOrThrow("comment")),
                    name
                    ));

        }
        for (MuseumReview x : list)
        {
            Log.d("Review",x.toString());
        }

        return list;
    }
    private String getName(int idUser){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.query(
                "USER",
                new String[]{"id", "Username"},
                "id = " + idUser,
                null,
                null,
                null,
                null
        );
        String name = "";
        while(cursor.moveToNext())
        {
            name = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
        }
        Log.d("Name", name);
        return name;
    }
}
