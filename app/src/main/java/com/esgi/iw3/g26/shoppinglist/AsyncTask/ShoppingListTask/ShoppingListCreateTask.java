package com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask;

import android.util.Log;

import org.json.JSONObject;

import static java.lang.StrictMath.abs;

public class ShoppingListCreateTask extends AbstractShoppingListTask {

    private String name;

    public ShoppingListCreateTask(String token, String name) {
        super(token);
        this.name = name;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "name" + "=" + this.name;

        // API call
        Log.d("shoppingList:create", SHOPPING_LIST_CREATE_URL + queryParameters);
        return getURL(SHOPPING_LIST_CREATE_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        //TODO: api logic, cast
        if (response.has("code") && response.optInt("code") == 0) {
            this.listener.onSuccess(response.optJSONObject("result"));
        } else {
            this.listener.onApiError(response);
        }
    }
}
