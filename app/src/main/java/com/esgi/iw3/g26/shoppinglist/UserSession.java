package com.esgi.iw3.g26.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.esgi.iw3.g26.shoppinglist.Entity.User;

public class UserSession {

    public static final String KEY_EMAIL = "email";
//    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER_LOGGED_IN = "isUserLoggedIn";

    private Context context;
    private Editor editor;
    private SharedPreferences preferences;
    private int PRIVATE_MODE = 0;
    private String PREFERNCE_NAME = "userSession";

    public UserSession(Context context) {
        this.context = context;
        this.preferences = this.context.getSharedPreferences(PREFERNCE_NAME,PRIVATE_MODE);
        this.editor = this.preferences.edit();
    }

    public void createUserLoginSession(User user) {
        editor.putBoolean(KEY_USER_LOGGED_IN, true);
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_TOKEN, user.getToken());
        editor.putString(KEY_FIRSTNAME, user.getFirstName());
        editor.putString(KEY_LASTNAME, user.getLastName());
    }

    public boolean checkLogin() {
        if (!this.isUserLoggedIn()) {
            this.redirectToLogin();
            return true;
        }
        return false;
    }

    public User getUserLoggedId() {
        return new User(
                preferences.getString(KEY_FIRSTNAME, null),
                preferences.getString(KEY_LASTNAME, null),
                preferences.getString(KEY_EMAIL, null)
        );
    }

    /**
     * Destroy user's session and open login page
     */
    public void logoutUser() {
        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
        this.redirectToLogin();
    }

    public boolean isUserLoggedIn() {
        return this.preferences.getBoolean(KEY_USER_LOGGED_IN, false);
    }

    public String getToken() {
        checkLogin();
        return preferences.getString(KEY_TOKEN, null);
    }

    private void redirectToLogin() {
        // After logout redirect user to MainActivity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        context.startActivity(i);
    }

}
