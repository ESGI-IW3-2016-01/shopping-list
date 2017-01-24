package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ShoppingListActivity extends ListActivity implements IHttpRequestListener {


    TextView content;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        content = (TextView)findViewById(R.id.nameList);

        //listView = (ListView) findViewById(R.id.nameList);
        String[] listValues = "!!! Récupérer avec les Task !!!";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValues);

        setListAdapter(adapter);
    }




    @Override
    public void onSuccess(JSONObject object) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(String message) {

    }
}
