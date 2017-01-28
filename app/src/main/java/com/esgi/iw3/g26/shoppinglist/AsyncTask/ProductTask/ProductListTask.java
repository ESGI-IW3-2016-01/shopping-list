package com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask;

import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
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
                        "&" + "shopping_list_id" + "=" + this.shoppingListId;

        // API call
        Log.d("product:list", PRODUCT_LIST_URL + queryParameters);
        return getURL(PRODUCT_LIST_URL + queryParameters);
    }
    @Override
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
