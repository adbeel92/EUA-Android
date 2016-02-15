package com.eua.SalesTrackingApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by rubymobile on 2/2/16.
 */
public class UserSessionManager {
    private static UserSessionManager self;
    private final SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "EUA";
    private static final String IS_USER_LOGGED_IN = "IsUserLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "id";
    public static final String KEY_REMEMBER_PASSWORD = "rememberPassword";
    //public static final String KEY_TOKEN = "token";

    public UserSessionManager(Context context){
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static UserSessionManager getInstance(Context context) {
        if (self == null) {
            self = new UserSessionManager(context);
        }
        return self;
    }

    //Create login session
    public void createUserLoginSession(String name, String email, String id, Boolean rememberPassword){
        editor.putBoolean(IS_USER_LOGGED_IN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ID, id);
        editor.putBoolean(KEY_REMEMBER_PASSWORD, rememberPassword);
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return true;
        }
        return false;
    }


    public String getLoggedUserEmail(){
        return pref.getString(KEY_EMAIL, null);
    }
    public String getLoggedUserName(){
        return pref.getString(KEY_NAME, null);
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        if (!isPasswordRememberable()){
            editor.clear();
            editor.commit();
        }
    }


    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGGED_IN, false);
    }

    // Check for rememberable password
    public boolean isPasswordRememberable(){
        return pref.getBoolean(KEY_REMEMBER_PASSWORD, false);
    }
}