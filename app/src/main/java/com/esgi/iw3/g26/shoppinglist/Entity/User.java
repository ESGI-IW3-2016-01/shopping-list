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
            this.firstName = object.getString("firstname");
            this.lastName = object.getString("lastname");
            this.email = object.getString("email");
            this.token = object.getString("token");
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
        map.put("firstname", this.firstName);
        map.put("lastname", this.lastName);
        map.put("email", this.email);
        return map;
    }
}
