package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductListTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListListTask;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ProductsActivity extends ListActivity implements IHttpRequestListener {

    TextView content;
    ListView listView;
    private ProductListTask listProductTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

//        listView = (ListView) findViewById(R.id.list);

        String token = "123145644897";
        String listId = "12" ;

        listProductTask = new ProductListTask(token, listId);
        listProductTask.execute();

        // TEMPORAIRE
        String[] listValues = new String[0];
        listValues[0] = listProductTask.toString();
        // TEMPORAIRE


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
    public void onApiError(JSONObject object) {

    }
}
