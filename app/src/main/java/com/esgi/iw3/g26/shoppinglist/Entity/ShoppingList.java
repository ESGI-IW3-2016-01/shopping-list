package com.esgi.iw3.g26.shoppinglist.Entity;

import com.esgi.iw3.g26.shoppinglist.Interface.IHashMapSerialize;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ShoppingList implements IHashMapSerialize {

    private Integer id;
    private String name;
    private Date createdAt;
    private Boolean completed;

    public static final String SHOPPING_LIST_ID_KEY = "id";
    public static final String SHOPPING_LIST_NAME_KEY = "name";
    public static final String SHOPPING_LIST_DATE_KEY = "created_date";
    public static final String SHOPPING_LIST_COMPLETED_KEY = "completed";

    public static final String SHOPPING_LIST_TEXT_1 = "text1";
    public static final String SHOPPING_LIST_TEXT_2 = "text2";

    /**
     * Constructor
     *
     * @param id        Shopping List id
     * @param name      Shopping List name
     * @param createdAt Shopping List date of creation
     * @param completed Shopping List is completed or not
     */
    public ShoppingList(int id, String name, Date createdAt, boolean completed) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.completed = completed;
    }

    public ShoppingList(int id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = new Date();
        this.completed = false;
    }

    public ShoppingList(JSONObject object) {
        Boolean completed = object.optString(SHOPPING_LIST_COMPLETED_KEY).equals("1");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = format.parse(object.optString(SHOPPING_LIST_DATE_KEY));
        } catch (Exception e) {
        }
        this.id = object.optInt("id");
        this.name = object.optString(SHOPPING_LIST_NAME_KEY);
        this.createdAt = date;
        this.completed = completed;
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
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        DateFormat df2 = new SimpleDateFormat("MMMM dd, yyyy");
        String todo = this.completed ? "(completed)" : "(active)";

        HashMap<String, String> map = new HashMap<>();
        map.put(SHOPPING_LIST_ID_KEY, this.id.toString());
        map.put(SHOPPING_LIST_NAME_KEY, this.name);
        map.put(SHOPPING_LIST_DATE_KEY, df2.format(this.createdAt).toString());
        map.put(SHOPPING_LIST_COMPLETED_KEY, this.completed ? "1" : "0");
        //User to display in listView
        map.put(SHOPPING_LIST_TEXT_1, todo + " " + this.name);
        map.put(SHOPPING_LIST_TEXT_2, "Created on " + df.format(this.createdAt));
        return map;
    }
}