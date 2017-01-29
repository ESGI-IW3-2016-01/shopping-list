package com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask;

import android.util.Log;

import org.json.JSONObject;

import static java.lang.StrictMath.abs;

public class ProductRemoveTask extends AbstractProductTask {

    private String id;

    public ProductRemoveTask(String token, String id) {
        super(token);
        this.id = id;
        Log.i("testDelte","Delete");
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        Log.i("testDelte","Delete2");
        String queryParameters =
                "?" + "token" + "=" + this.token +
                        "&" + "id" + "=" + this.id;

        // API call
        Log.i("product:remove", PRODUCT_CREATE_URL + queryParameters);
        return getURL(PRODUCT_REMOVE_URL + queryParameters);
    }
}
