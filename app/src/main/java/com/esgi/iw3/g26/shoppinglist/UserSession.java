package com.esgi.iw3.g26.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.esgi.iw3.g26.shoppinglist.Activity.User.LoginActivity;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Entity.User;

public class UserSession {

    private Context _context;
    private Editor editor;
    private SharedPreferences preferences;
    private static final int PRIVATE_MODE = 0;
    private static final String PREFERENCE_NAME = "userSession";
    private static final String KEY_USER_LOGGED_IN = "isUserLoggedIn";

    public UserSession(Context context) {
        this._context = context;
        this.preferences =  _context.getSharedPreferences(PREFERENCE_NAME,PRIVATE_MODE);
        this.editor = preferences.edit();
    }

    public void createUserLoginSession(User user) {
        editor.putBoolean(KEY_USER_LOGGED_IN, true);
        editor.putString(User.USER_EMAIL_KEY, user.getEmail());
        editor.putString(User.USER_TOKEN_KEY, user.getToken());
        editor.putString(User.USER_FIRST_NAME_KEY, user.getFirstName());
        editor.putString(User.USER_LAST_NAME_KEY, user.getLastName());
        editor.commit();
    }

    public boolean checkLogin() {
        if (!this.isUserLoggedIn()) {
            this.logoutUser();
            this.redirectToLogin();
            return false;
        }
        return true;
    }

    public void saveToken(String token) {
        editor.putString("token",token);
        editor.commit();
    }

    public User getUserLoggedId() {
        return new User(
                preferences.getString(User.USER_FIRST_NAME_KEY, null),
                preferences.getString(User.USER_LAST_NAME_KEY, null),
                preferences.getString(User.USER_EMAIL_KEY, null)
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
        return preferences.getString(User.USER_TOKEN_KEY,null);
    }

    public void putShoppingListId(Integer id) {
        editor.putInt(ShoppingList.SHOPPING_LIST_ID_KEY,id);
        editor.commit();
    }

    public boolean hasShoppingListId() {
        int id = preferences.getInt(ShoppingList.SHOPPING_LIST_ID_KEY,0);
        return id == 0;
    }

    public Integer getShoppingListId() {
       return preferences.getInt(ShoppingList.SHOPPING_LIST_ID_KEY,0);
    }

    private void redirectToLogin() {
        // After logout redirect user to MainActivity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }

}
