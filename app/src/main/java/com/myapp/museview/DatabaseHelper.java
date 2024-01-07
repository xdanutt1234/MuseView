package com.myapp.museview;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;
import android.util.LogPrinter;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String name = "BazaDate_Museview.db";
    private static final int version = 49;
    public DatabaseHelper(@Nullable Context context) {
        super(context, name , null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String createTableQueryUser = "CREATE TABLE User ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Username TEXT UNIQUE,"
                + "Email TEXT UNIQUE,"
                + "Password TEXT);";
        db.execSQL(createTableQueryUser);
       /* String createTableQueryMuseum = "CREATE TABLE Museum ("
                + "idMuseum INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "museumName TEXT,"
                + "museumDescription TEXT,"
                + "mapLocation TEXT,"
                + "imageName TEXT);";
        db.execSQL(createTableQueryMuseum);*/
        /*String createTableQueryMarker = "CREATE TABLE Marker (" +
                "idMarker INTEGER PRIMARY KEY AUTOINCREMENT," +
                "markerName TEXT," +
                "markerDescription TEXT," +
                "xPos REAL," +
                "yPos REAL," +
                "museum INTEGER," +
                "markerimage TEXT);";
        db.execSQL(createTableQueryMarker);*/

        String createTableQueryMuseumReview = "CREATE TABLE MuseumReview (" +
                "idMuseumReview INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUser INTEGER," +
                "idMuseum INTEGER," +
                "rating INTEGER," +
                "comment TEXT);";
        db.execSQL(createTableQueryMuseumReview);

    }

    public  void debugCreateMuseumTable()
    {   SQLiteDatabase db = this.getWritableDatabase();
        String createTableQueryMuseum = "CREATE TABLE Museum ("
                + "idMuseum INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "museumName TEXT,"
                + "museumDescription TEXT,"
                + "mapLocation TEXT);";
        db.execSQL(createTableQueryMuseum);
        Log.d("CREATEDMUSEUMTABLE", "debugCreateMuseumTable: ");
    }

    public void debugDeleteMuseum()
    {   SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + "Museum");
    }
    public void debugCreateMuseum(String name, String description, String location, String image)
    {   SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("museumName", name);
        values.put("museumDescription", description);
        values.put("mapLocation", location);
        values.put("imageName", image);
        db.insert("Museum",null,values);
    }
    public void debugCreateMarker(String name, String description, float x, float y, int museum, String markerimage)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("markerName", name);
        values.put("markerDescription", description);
        values.put("xPos",x);
        values.put("yPos",y);
        values.put("museum",museum);
        values.put("markerimage",markerimage);
        db.insert("Marker",null,values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "User");
        //db.execSQL("DROP TABLE IF EXISTS " + "Museum");
        //db.execSQL("DROP TABLE IF EXISTS " + "Marker");
        db.execSQL("DROP TABLE IF EXISTS " + "MuseumReview");

        onCreate(db);
    }
}
