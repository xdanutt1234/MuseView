/**
 * Activitate care gestionează scanarea codurilor QR pentru a accesa informații despre un muzeu.
 */
package com.myapp.museview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class QRActivity extends CaptureActivityPortrait {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        IntentIntegrator intentIntegrator = new IntentIntegrator(QRActivity.this);
        intentIntegrator.setPrompt("Scan a MuseView QR code");
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
    }
    /**
     * Metodă apelată la obținerea rezultatelor de la activitățile lansate pentru a prelua date.
     *
     * @param requestCode Codul cererii pentru activitatea lansată.
     * @param resultCode  Codul rezultatului întors de activitatea lansată.
     * @param data        Intentul care conține datele returnate de activitatea lansată.
     */
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

                Cursor cursor = null;
                List<Museum> list = new ArrayList<>();

                cursor = db.query(
                        "MUSEUM",
                        new String[]{"IDMUSEUM","MUSEUMNAME","MUSEUMDESCRIPTION","MAPLOCATION", "IMAGENAME"},
                        "IDMUSEUM = " + intentResult.getContents(),
                        null,
                        null,
                        null,
                        null
                );
                list.clear();
                while (cursor.moveToNext())
                {
                    list.add(new Museum(cursor.getInt(cursor.getColumnIndexOrThrow("idMuseum")), cursor.getString(cursor.getColumnIndexOrThrow("museumName")), cursor.getString(cursor.getColumnIndexOrThrow("museumDescription")),cursor.getString(cursor.getColumnIndexOrThrow("mapLocation")),cursor.getString(cursor.getColumnIndexOrThrow("imageName"))));
                }
                toMuseum(list.get(0));

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Metodă pentru a naviga către activitatea detaliilor muzeului pe baza informațiilor obținute.
     *
     * @param museum Obiectul Museum care conține informații despre muzeu.
     */
    private void toMuseum(Museum museum)
    {
        Intent intent = new Intent(this, MuseumDetailsActivity.class);
        intent.putExtra("id",museum.getId());
        intent.putExtra("name",museum.getName());
        intent.putExtra("description",museum.getDescription());
        intent.putExtra("image",museum.getImage());
        intent.putExtra("map",museum.getLocation());
        startActivity(intent);
    }
}
