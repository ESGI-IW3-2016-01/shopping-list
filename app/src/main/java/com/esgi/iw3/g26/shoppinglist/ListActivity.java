package com.esgi.iw3.g26.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.content.Intent;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductListTask;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends Activity implements IHttpRequestListener {

    TextView textView;
    Button button;

    private ProductListTask productListTask;
    private FloatingActionButton fab;
    private ListView listView;
    private UserSession session;
    private List<HashMap<String, String>> shoppingProductList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        this.session = new UserSession(getApplicationContext());

        Intent intent = getIntent();
        String listName = intent.getStringExtra(ShoppingList.SHOPPING_LIST_NAME_KEY);
        String listDate = intent.getStringExtra(ShoppingList.SHOPPING_LIST_DATE_KEY);
        String listId = intent.getStringExtra(ShoppingList.SHOPPING_LIST_ID_KEY);

        TextView textView = (TextView) findViewById(R.id.textView);

        String isActive;
        if (ShoppingList.SHOPPING_LIST_COMPLETED_KEY.equals("1")) {
            isActive = "Completed";
        } else {
            isActive = "Active";
        }
        textView.setText(listName + " " + listDate + " " + isActive+ " " + listId);

        listView = (ListView) findViewById(android.R.id.list);
        productListTask = new ProductListTask(session.getToken(), listId);
        productListTask.setListener(this);
        productListTask.execute();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateProductActivity.class);
                startActivity(i);
            }
        });

        simpleAdapter = new SimpleAdapter(this,
                shoppingProductList,
                android.R.layout.simple_list_item_2,
                new String[]{ShoppingList.SHOPPING_LIST_NAME_KEY, ShoppingList.SHOPPING_LIST_DATE_KEY},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.i("activity:List:success", object.toString());

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }
}
