package com.esgi.iw3.g26.shoppinglist.AsyncTask;

import org.json.JSONObject;

public abstract class AbstractApiTask extends AbstractTask {

    protected final String token;

    protected  AbstractApiTask(String token) {
        this.token = token;
    }

    @Override
    protected void onPostExecute(JSONObject response) {
        if (response.has("code") && response.optInt("code") == 0) {
            this.listener.onSuccess(response.optJSONObject("result"));
        } else {
            this.listener.onApiError(response);
        }
    }
}
