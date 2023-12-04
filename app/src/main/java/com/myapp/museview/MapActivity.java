package com.myapp.museview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        ConstraintLayout mapContainer = findViewById(R.id.mapContainer);
        int idmuseum = intent.getIntExtra("id",-1);
        String namemuseum = intent.getStringExtra("name");
        List<Marker> list = performMarkerSearch(idmuseum);
        ImageView map = findViewById(R.id.mapView);
        mapContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mapContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                for (Marker marker : list)
                {   Log.d("MapActivity", "Width: " + mapContainer.getWidth());
                    Log.d("MapActivity", "Height: " + mapContainer.getHeight());
                    float x = marker.getX() * mapContainer.getWidth();
                    float y = marker.getY() * mapContainer.getHeight();
                    Log.d("MapActivity", "Calculated X: " + x);
                    Log.d("MapActivity", "Calculated Y: " + y);
                    View markerView = new View(MapActivity.this);
                    markerView.setBackgroundResource(R.drawable.pin);

                    markerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMarkerInfoPopup(marker, mapContainer, markerView);
                        }
                    });

                    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100,100);
                    params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                    params.leftMargin=Math.round(x);
                    params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                    params.topMargin=Math.round(y);
                    Log.d("MapActivity", "Actual X: " + params.leftMargin);
                    Log.d("MapActivity", "Actual Y: " + params.topMargin);
                    mapContainer.addView(markerView,params);
                }
            }
        });


    }
    private List<Marker> performMarkerSearch(int idmuseum)
    {   DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        List<Marker> list = new ArrayList<>();

        cursor = db.query(
                "MARKER",
                new String[]{"IDMARKER","MARKERNAME","MARKERDESCRIPTION","XPOS", "YPOS", "MUSEUM", "MARKERIMAGE"},
                "MUSEUM = " + idmuseum,
                null,
                null,
                null,
                null
        );
        list.clear();
        while (cursor.moveToNext())
        {
            list.add(new Marker(cursor.getInt(cursor.getColumnIndexOrThrow("idMarker")),
                    cursor.getString(cursor.getColumnIndexOrThrow("markerName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("markerDescription")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("xPos")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("yPos")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("museum")),
                    cursor.getString(cursor.getColumnIndexOrThrow("markerimage"))));
        }


        return list;
    }
    private void showMarkerInfoPopup(Marker marker, ConstraintLayout mapContainer, View anchorView) {
        // Inflate the marker info layout
        View popupView = getLayoutInflater().inflate(R.layout.info_exponat, null);

        // Initialize views in the popup layout
        TextView textMarkerName = popupView.findViewById(R.id.textMarkerName);
        TextView textMarkerDescription = popupView.findViewById(R.id.textMarkerDescription);
        ImageView imageMarker = popupView.findViewById(R.id.imageMarker);

        // Set marker information to views
        textMarkerName.setText(marker.getName());
        textMarkerDescription.setText(marker.getDescription());

        // Load image using your preferred image loading library (e.g., Picasso, Glide)
        // For simplicity, assume marker.getImage() returns a resource ID
        imageMarker.setImageResource(getResources().getIdentifier(marker.getImage(), "drawable", getPackageName()));

        // Create a PopupWindow

        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        int xOffset = location[0] - popupView.getWidth()/2;
        int yOffset = location[1] - popupView.getHeight() - 20;
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(300); // Adjust the duration as needed
        popupView.startAnimation(fadeIn);
        popupWindow.showAtLocation(mapContainer, Gravity.NO_GRAVITY, xOffset, yOffset);
        popupWindow.setOutsideTouchable(true);
    }
}
