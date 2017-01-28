package com.esgi.iw3.g26.shoppinglist.Entity;

import android.util.Log;

import com.esgi.iw3.g26.shoppinglist.Interface.IHashMapSerialize;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Antoine on 11/11/2016.
 */

public class User implements IHashMapSerialize {

    private String firstName;
    private String lastName;
    private String email;
    private String token;

    public final static String USER_FIRST_NAME_KEY = "firstname";
    public final static String USER_LAST_NAME_KEY = "lastname";
    public final static String USER_EMAIL_KEY = "email";
    public final static String USER_TOKEN_KEY = "token";

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
    }

    public User(JSONObject object) {
        try {
            if (object.has("result")) {
                object = object.getJSONObject("result");
            }
            this.firstName = object.getString(USER_FIRST_NAME_KEY);
            this.lastName = object.getString(USER_LAST_NAME_KEY);
            this.email = object.getString(USER_EMAIL_KEY);
            this.token = object.getString(USER_TOKEN_KEY);
        } catch (Exception e) {
            Log.d("user:instantiation", e.getMessage());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean equals(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            return (this.email.equals(user.email) &&
                    this.lastName.equals(user.lastName) &&
                    this.lastName.equals(user.lastName));
        } else {
            return false;
        }
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put(USER_FIRST_NAME_KEY, this.firstName);
        map.put(USER_LAST_NAME_KEY, this.lastName);
        map.put(USER_EMAIL_KEY, this.email);
        return map;
    }
}
