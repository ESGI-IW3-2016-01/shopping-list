package com.esgi.iw3.g26.shoppinglist.AsyncTask;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Login Success
// {"code":"0","result":{"lastname":"","firstname":"","email":"","token":"d41d8cd98f00b204e9800998ecf8427e"}}

// Login failed
// {"code":"3","msg":"Login failed check your credentials"}

public class UserLoginTask extends AsyncTask<Void, Long, JSONObject> {

    private IHttpRequestListener listener;

    public void setListener(IHttpRequestListener listener) {
        this.listener = listener;
    }

    private final String mEmail;
    private final String mPassword;
    private final String USER_LOGIN_URL = "http://appspaces.fr/esgi/shopping_list/account/login.php";

    private boolean success = false;

    public UserLoginTask(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String queryParameters = "?" + "email" + "=" + this.mEmail + "&" + "password" + "=" + this.mPassword;
        // API call
        Log.d("doInBackground",queryParameters);
        return this.getURL(USER_LOGIN_URL + queryParameters);
    }

    protected void onPostExecute(JSONObject response) {

        try {
            int code = response.has("code") ? response.getInt("code") : 1;
            if(code == 0) { // Login successfull
                this.success = true;
                this.listener.onSuccess(response);
                //TODO Save user information and token
            } else {
                this.listener.onApiError("error");
            }
        } catch (JSONException e) {
            this.listener.onFailure(e.getMessage());
        }
    }

    @Override
    protected void onCancelled() {
        this.listener.onFailure("cancelled");
    }
    
    /**
     * HTTP GET Request
     *
     * @param url API URL to get
     * @return Response as a string
     */
    protected JSONObject getURL(String url) {
        String response;
            JSONObject object = new JSONObject();

            try {
                URL uri = new URL(url);
                Log.d("getUrl", url);
                HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);
                object = new JSONObject(response);
                Log.d("getUrl", response);
            } catch (Exception e) {
                response = e.getMessage();
                Log.d(e.getClass().toString(), response);
                this.listener.onFailure(response);
            }
        return object;
    }

    /**
     * Converts response stream to simple string
     *
     * @param inputStream Request input stream
     * @return Response as a string
     */
    @NonNull
    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(e.getClass().toString(), e.getMessage());
            this.listener.onFailure(e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(e.getClass().toString(), e.getMessage());
                this.listener.onFailure(e.getMessage());
            }
        }
        return sb.toString();
    }
}
