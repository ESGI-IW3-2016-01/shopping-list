package com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask;

import android.util.Log;

import org.json.JSONObject;

public class ShoppingListRemoveTask extends AbstractShoppingListTask {

    private String id;

    public ShoppingListRemoveTask(String token, String id) {
        super(token);
        this.id = id;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "id" + "=" + this.id;

        // API call
        Log.d("shoppingList:remove", SHOPPING_LIST_REMOVE_URL + queryParameters);
        return getURL(SHOPPING_LIST_REMOVE_URL + queryParameters);
    }
}
