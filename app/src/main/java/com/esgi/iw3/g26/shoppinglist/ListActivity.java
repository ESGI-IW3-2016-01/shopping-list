package com.esgi.iw3.g26.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListCreateTask;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ListActivity extends Activity implements IHttpRequestListener {

    TextView textView;
    Button button;

    private ShoppingListCreateTask createListTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        addListenerOnButton();

    }


    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.list_saveList_button);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                textView = (TextView)findViewById(R.id.nameList);

                String nameList = textView.getText().toString();

                String token = "123145644897";
                createListTask = new ShoppingListCreateTask(token, nameList);
                createListTask.execute();

            }

        });

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
