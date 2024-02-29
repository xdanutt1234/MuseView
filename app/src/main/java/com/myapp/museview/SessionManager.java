/**
 * Manager de sesiune pentru gestionarea informațiilor de autentificare.
 * Acest manager utilizează SharedPreferences pentru a stoca și accesa datele sesiunii.
 */
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

    /**
     * Începe o sesiune de autentificare și stochează informațiile utilizatorului.
     *
     * @param userID   ID-ul utilizatorului
     * @param username Numele de utilizator
     */
    public void startSession(int userID, String username)
    {
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, userID);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }
    /**
     * Încheie sesiunea de autentificare și șterge toate informațiile stocate.
     */
    public void endSession()
    {
        editor.clear();
        editor.apply();
    }
    /**
     * Verifică dacă există o sesiune de autentificare activă.
     *
     * @return true dacă există o sesiune activă, false altfel
     */
    public boolean isLoggedIn()
    {
        return pref.getBoolean(KEY_LOGGED_IN, false);
    }

    /**
     * Obține ID-ul utilizatorului din sesiune.
     *
     * @return ID-ul utilizatorului sau -1 dacă nu există o sesiune activă
     */
    public int getUserID()
    {
        return pref.getInt(KEY_USER_ID, -1);
    }
    /**
     * Obține numele de utilizator din sesiune.
     *
     * @return Numele de utilizator sau "NONE" dacă nu există o sesiune activă
     */
    public String getUsername()
    {
        return pref.getString(KEY_USERNAME, "NONE");
    }
}
