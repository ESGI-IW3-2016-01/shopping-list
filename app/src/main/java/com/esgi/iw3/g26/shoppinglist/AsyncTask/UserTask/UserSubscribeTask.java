package com.esgi.iw3.g26.shoppinglist.AsyncTask.UserTask;

import android.util.Log;

import org.json.JSONObject;

public class UserSubscribeTask extends AbstractUserTask {

    private final String mFirstName;
    private final String mLastName;

    public UserSubscribeTask(String email, String password, String firstName, String lastName) {
        super(email, password);
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public UserSubscribeTask(String email, String password, String firstName) {
        this(email, password, firstName, null);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters = "?" + "email" + "=" + this.mEmail +
                "&" + "password" + "=" + this.mPassword +
                "&" + "firstname" + "=" + this.mFirstName;
        if (null != this.mLastName) {
            queryParameters += "&" + "lastname" + "=" + this.mLastName;
        }
        // API call
        Log.d("user:subscribe", USER_SUBSCRIBE_URL + queryParameters);
        return getURL(USER_SUBSCRIBE_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        if (response.has("code") && response.optInt("code") == 0) {
            this.listener.onSuccess(response.optJSONObject("result"));
        } else {
            this.listener.onApiError(response);
        }
    }
}
