package com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask;

import android.util.Log;

import org.json.JSONObject;

import static java.lang.StrictMath.abs;

public class ProductEditTask extends AbstractProductTask {

    private String id;
    private String name;
    private Integer quantity;
    private Double price;

    public ProductEditTask(String token, String id, Double price, String name, Integer quantity) {
        super(token);
        this.id = id;
        this.price = abs(price);
        this.name = name;
        this.quantity = quantity == 0 ? 1 : abs(quantity);
    }

    public ProductEditTask(String token, String id, Double price) {
        this(token,id,price,null,null);
    }

    public ProductEditTask(String token, String id, Double price, String name) {
        this(token,id,price,name,null);
    }

    public ProductEditTask(String token, String id, Double price, Integer quantity) {
        this(token,id,price,null,quantity);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "id" + "=" + this.id +
                        "&" + "price" + "=" + this.price;
        if (null != this.name) {
            queryParameters += "&" + "name" + "=" + this.name;
        }
        if (null != this.quantity) {
            queryParameters += "&" + "quantity" + "=" + this.quantity;
        }

        // API call
        Log.d("product:edit", PRODUCT_EDIT_URL + queryParameters);
        return getURL(PRODUCT_EDIT_URL + queryParameters);
    }
}
