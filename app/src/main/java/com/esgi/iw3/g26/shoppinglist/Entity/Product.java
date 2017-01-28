package com.esgi.iw3.g26.shoppinglist.Entity;

import com.esgi.iw3.g26.shoppinglist.Interface.IHashMapSerialize;

import java.util.HashMap;

import android.util.Log;

import org.json.JSONObject;

public class Product implements IHashMapSerialize {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;

    public static final String PRODUCT_ID_KEY = "id";
    public static final String PRODUCT_NAME_KEY = "name";
    public static final String PRODUCT_QUANTITY_KEY = "quantity";
    public static final String PRODUCT_PRICE_KEY = "price";

    public static final String PRODUCT_LIST_TEXT_1 = "text1";
    public static final String PRODUCT_LIST_TEXT_2 = "text2";

    /**
     * Constructor
     * @param id
     * @param name
     * @param quantity
     * @param price
     */
    public Product(int id, String name, Integer quantity, Double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(JSONObject object) {
        try {
            if(object.has("result")) {
                object = object.getJSONObject("result");
            }
            this.id = object.getInt("id");
            this.name = object.getString(PRODUCT_NAME_KEY);
            this.quantity = object.optInt(PRODUCT_QUANTITY_KEY);
            this.price = object.optDouble(PRODUCT_PRICE_KEY);
        } catch (Exception e) {
            Log.d("product:instantiation",e.getMessage());
        }
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
        map.put(PRODUCT_LIST_TEXT_1, name + " (x " + quantity + ")");
        map.put(PRODUCT_LIST_TEXT_2, "$" + price);

        map.put(PRODUCT_ID_KEY, this.id.toString());
        map.put(PRODUCT_NAME_KEY, this.name);
        map.put(PRODUCT_QUANTITY_KEY, this.quantity.toString());
        map.put(PRODUCT_PRICE_KEY, this.price.toString());
        return map;
    }
}
