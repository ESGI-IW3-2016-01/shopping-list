package com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShoppingListListTask extends AbstractShoppingListTask {

    public ShoppingListListTask(String token) {
        super(token);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token;

        // API call
        Log.d("shoppingList:list", SHOPPING_LIST_LIST_URL + queryParameters);
        return getURL(SHOPPING_LIST_LIST_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        try {
            if(response.has("code") && response.getInt("code") == 0) {
                this.listener.onSuccess(response);
            } else {
                this.listener.onApiError(response);
            }
        } catch (JSONException e) {
            this.listener.onFailure(e.getMessage());
        }
    }
}
