package com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask;

import android.util.Log;

import org.json.JSONObject;

import static java.lang.StrictMath.abs;

public class ProductListTask extends AbstractProductTask {

    private String shoppingListId;

    public ProductListTask(String token, String shoppingListId) {
        super(token);
        this.shoppingListId = shoppingListId;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "shoppingListId" + "=" + this.shoppingListId;

        // API call
        Log.d("product:list", PRODUCT_LIST_URL + queryParameters);
        return getURL(PRODUCT_LIST_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        //TODO: api logic
    }
}
