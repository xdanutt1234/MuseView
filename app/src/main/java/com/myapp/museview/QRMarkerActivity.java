package com.myapp.museview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class QRMarkerActivity extends CaptureActivityPortrait {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        IntentIntegrator intentIntegrator = new IntentIntegrator(QRMarkerActivity.this);
        intentIntegrator.setPrompt("Scan a Marker QR code");
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                Intent intent = getIntent();
                int idmuseum = intent.getIntExtra("id",-1);
                Cursor cursor = null;
                List<Marker> list = new ArrayList<>();

                cursor = db.query(
                        "MARKER",
                        new String[]{"IDMARKER","MARKERNAME","MARKERDESCRIPTION","XPOS", "YPOS", "MUSEUM", "MARKERIMAGE"},
                        "IDMARKER = " + intentResult.getContents(),
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
                toMuseum(intent,list.get(0));

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void toMuseum(Intent intent, Marker marker)
    {
        Intent intent1 = new Intent(this, MapActivity.class);
        intent1.putExtras(intent);
        intent1.putExtra("x",marker.getX());
        intent1.putExtra("y",marker.getY());
        startActivity(intent1);
    }
}