package com.esgi.iw3.g26.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

/**
 * Created by Jolan on 24/01/2017.
 */

public class SubscribeActivity extends Activity implements IHttpRequestListener {

    TextView content;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

    }


    @Override
    public void onSuccess(JSONObject object) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }
}
