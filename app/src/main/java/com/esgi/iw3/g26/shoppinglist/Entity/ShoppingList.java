package com.esgi.iw3.g26.shoppinglist.Entity;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Antoine on 11/11/2016.
 */

public class ShoppingList {

    private int id;
    private String name;
    private Date createdAt;
    private boolean completed;
    /* TODO Liste de produits ? */

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
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
}