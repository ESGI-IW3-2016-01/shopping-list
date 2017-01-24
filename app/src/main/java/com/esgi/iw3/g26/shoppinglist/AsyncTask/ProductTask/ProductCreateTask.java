package com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask;

import android.util.Log;

import org.json.JSONObject;

import static java.lang.StrictMath.abs;

public class ProductCreateTask extends AbstractProductTask {

    private String shoppingListId;
    private String name;
    private int quantity;
    private double price;

    public ProductCreateTask(String token, String shoppingListId, String name, int quantity, double price) {
        super(token);
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.quantity = quantity == 0 ? 1 : abs(quantity);
        this.price = abs(price);
    }

    public ProductCreateTask(String token, String shoppingListId, String name, int quantity) {
        this(token, shoppingListId, name, quantity, 0.0);
    }

    public ProductCreateTask(String token, String shoppingListId, String name, double price) {
        this(token, shoppingListId, name, 1, price);
    }

    public ProductCreateTask(String token, String shoppingListId, String name) {
        this(token, shoppingListId, name, 1, 0.0);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "shopping_list_id" + "=" + this.shoppingListId +
                        "&" + "name" + "=" + this.name +
                        "&" + "quantity" + "=" + this.quantity +
                        "&" + "price" + "=" + this.price;

        // API call
        Log.d("product:create", PRODUCT_CREATE_URL + queryParameters);
        return getURL(PRODUCT_CREATE_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        //TODO: api logic
    }
}
