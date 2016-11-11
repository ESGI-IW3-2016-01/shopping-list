package com.esgi.iw3.g26.shoppinglist.Entity;

/**
 * Created by Antoine on 11/11/2016.
 */

public class Product {

    private int id;
    private String name;
    private int quantity;
    private double price;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
}
