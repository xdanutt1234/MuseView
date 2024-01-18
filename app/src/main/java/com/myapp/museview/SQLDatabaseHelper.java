package com.myapp.museview;

import android.util.Log;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLDatabaseHelper {
   /* Connection con;

    SQLDatabaseHelper(String url, String user, String password)
    {
        try {
            this.con = DriverManager.getConnection(url, user, password);
        } catch (java.sql.SQLException e)
        {
            Log.d("SQL","Error connecting to the database at " + url);
        }
    }
*/
    boolean checkLogin(String username, String password)
    {
       /* boolean state = false;
        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
            statement.setString(1,username);
            statement.setString(2,password);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
        } catch (java.sql.SQLException e)
        {
            Log.d("SQL", "Error while querrying in method checkLogin");
        }*/
        return true;
    }
}
