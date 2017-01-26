package com.esgi.iw3.g26.shoppinglist.Entity;

import com.esgi.iw3.g26.shoppinglist.Interface.IHashMapSerialize;

import java.util.HashMap;

/**
 * Created by Antoine on 11/11/2016.
 */

public class Product implements IHashMapSerialize {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;

    /**
     * Constructor
     * @param id
     * @param name
     * @param quantity
     * @param price
     */
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean equals(Object o) {
        if (o instanceof Product) {
            Product product = (Product) o;
            return (this.id == product.getId());
        } else {
            return false;
        }
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", this.id.toString());
        map.put("name", this.name);
        map.put("quantity", this.quantity.toString());
        map.put("price", this.price.toString());
        return map;
    }
}
