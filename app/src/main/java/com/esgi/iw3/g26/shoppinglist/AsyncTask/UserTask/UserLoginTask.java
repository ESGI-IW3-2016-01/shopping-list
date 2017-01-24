package com.esgi.iw3.g26.shoppinglist.AsyncTask.UserTask;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

// Login Success
// {"code":"0","result":{"lastname":"","firstname":"","email":"","token":"d41d8cd98f00b204e9800998ecf8427e"}}

// Login failed
// {"code":"3","msg":"Login failed check your credentials"}

public class UserLoginTask extends AbstractUserTask {

    public UserLoginTask(String email, String password) {
        super(email, password);
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters = "?" + "email" + "=" + this.mEmail + "&" + "password" + "=" + this.mPassword;
        // API call
        Log.d("user:login", USER_LOGIN_URL + queryParameters);
        return this.getURL(USER_LOGIN_URL + queryParameters);
    }

    @Override
    protected void onPostExecute(JSONObject response) {
        try {
            int code = response.has("code") ? response.getInt("code") : 1;
            if (code == 0) { // Login successful
                this.listener.onSuccess(response);
            } else { // Login Failed or Api Error
                this.listener.onApiError(response);
            }
        } catch (Exception e) { // Other errors
            Log.d("user:login:error", e.getMessage());
            this.listener.onFailure(e.getMessage());
        }
    }
}
