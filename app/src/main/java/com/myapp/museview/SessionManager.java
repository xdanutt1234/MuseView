package com.myapp.museview;

import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {

    private static final String PREF_NAME = "SesiuneLogare";
    private static final String KEY_USER_ID = "userID";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void startSession(int userID, String username)
    {
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, userID);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }
    public void endSession()
    {
        editor.clear();
        editor.apply();
    }
    public boolean isLoggedIn()
    {
        return pref.getBoolean(KEY_LOGGED_IN, false);
    }

    public int getUserID()
    {
        return pref.getInt(KEY_USER_ID, -1);
    }
    public String getUsername()
    {
        return pref.getString(KEY_USERNAME, "NONE");
    }
}
