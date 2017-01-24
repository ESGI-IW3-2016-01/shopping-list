package com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask;

import android.util.Log;

import org.json.JSONObject;

import static java.lang.StrictMath.abs;

public class ShoppingListEditTask extends AbstractShoppingListTask {

    private String id;
    private String name;
    private Boolean completed;
    // TODO Autre paramêtre de l'api ?

    public ShoppingListEditTask(String token, String id, String name, Boolean completed) {
        super(token);
        this.id = id;
        this.name = name;
        this.completed = completed;
    }

    public ShoppingListEditTask(String token, String id, Boolean completed) {
        this(token, id, null, completed);
    }

    public ShoppingListEditTask(String token, String id, String name) {
        this(token, id, name, null);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "id" + "=" + this.id;
        if (null != this.name) {
            queryParameters += "&" + "name" + "=" + this.name;
        }
        if (null != this.completed) {
            queryParameters += "&" + "completed" + "=" + this.completed;
        }

        // API call
        Log.d("shoppingList:edit", SHOPPING_LIST_EDIT_URL + queryParameters);
        return getURL(SHOPPING_LIST_EDIT_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        //TODO: api logic
    }
}
