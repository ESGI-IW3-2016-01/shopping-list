package com.esgi.iw3.g26.shoppinglist.Entity;

/**
 * Created by Antoine on 11/11/2016.
 */

public enum ResultCode {

    OK(0, "OK"),
    MISSING_PARAM(1, "Missing required parameter(s)"),
    EMAIL_REGISTERED(2, "Email already registered"),
    LOGIN_FAILED(3, "Login failed check your credentials"),
    INVALID_TOKEN(4, "Invalid token"),
    SERVER_ERROR(5, "Internal server error"),
    UNAUTHORIZED(6, "Unauthorized action"),
    NO_UPDATE(7, "Nothing to update");

    /**
     * Result code value
     */
    private int value;
    /**
     * Result code description
     */
    private String description;

    /**
     * Constructor
     * @param value
     * @param description
     */
    ResultCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
