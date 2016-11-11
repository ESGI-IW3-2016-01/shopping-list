package com.esgi.iw3.g26.shoppinglist.Entity;

/**
 * Created by Antoine on 11/11/2016.
 */

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String token;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
