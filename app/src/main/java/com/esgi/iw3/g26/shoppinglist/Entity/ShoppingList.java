package com.esgi.iw3.g26.shoppinglist.Entity;

import com.esgi.iw3.g26.shoppinglist.Interface.IHashMapSerialize;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

public class ShoppingList implements IHashMapSerialize {

    private Integer id;
    private String name;
    private Date createdAt;
    private Boolean completed;

    public static final String SHOPPING_LIST_NAME_KEY = "name";
    public static final String SHOPPING_LIST_DATE_KEY = "created_date";
    public static final String SHOPPING_LIST_COMPLETED_KEY = "completed";

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param createdAt
     * @param completed
     */
    public ShoppingList(int id, String name, Date createdAt, boolean completed) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.completed = completed;
    }

    public ShoppingList(JSONObject object) throws JSONException {
        this(object.optInt("id"),
                object.optString(SHOPPING_LIST_NAME_KEY),
                new Date(object.optString(SHOPPING_LIST_DATE_KEY)),
                object.optBoolean(SHOPPING_LIST_COMPLETED_KEY, false)
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public boolean equals(Object o) {
        if (o instanceof ShoppingList) {
            ShoppingList shoppingList = (ShoppingList) o;
            return (this.id == shoppingList.getId());
        } else {
            return false;
        }
    }

    @Override
    public HashMap<String, String> toHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", this.id.toString());
        map.put(SHOPPING_LIST_DATE_KEY, this.createdAt.toString());
        map.put(SHOPPING_LIST_COMPLETED_KEY, this.completed.toString());
        return map;
    }
}