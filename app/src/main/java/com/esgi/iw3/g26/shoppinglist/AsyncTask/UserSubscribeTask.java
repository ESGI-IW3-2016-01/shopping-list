package com.esgi.iw3.g26.shoppinglist.AsyncTask;

import android.support.annotation.NonNull
import android.util.Log;

import org.json.JSONObject;

public class UserSubscribeTask extends ApiTask {

    private final String mEmail;
    private final String mPassword;
    private final String mFirstName;
    private final String mLastName;
    private final static String USER_SUBSCRIBE_URL = "http://appspaces.fr/esgi/shopping_list/account/subscribe.php";

    public UserSubscribeTask(String email, String password, String firstName, String lastName) {
        this.mEmail = email;
        this.mPassword = password;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public UserSubscribeTask(String email, String password, String firstName) {
        this.mEmail = email;
        this.mPassword = password;
        this.mFirstName = firstName;
        this.mLastName = null;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters = "?" + "email" + "=" + this.mEmail +
                "&" + "password" + "=" + this.mPassword +
                "&" + "firstName" + "=" + this.mFirstName;
        if (null != this.mLastName) {
            queryParameters += "&" + "lastName" + "=" + this.mLastName;
        }
        // API call
        Log.d("Sub:doInBackground", USER_SUBSCRIBE_URL + queryParameters);
        return getURL(USER_SUBSCRIBE_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {
        //TODO: api logic
    }
}
