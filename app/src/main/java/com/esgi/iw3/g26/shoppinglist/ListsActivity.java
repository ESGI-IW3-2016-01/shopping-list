package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListCreateTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListListTask;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ListsActivity extends ListActivity implements IHttpRequestListener {

    TextView content;
    ListView listView;
    private ShoppingListListTask listListTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

//        content = (TextView)findViewById(R.id.nameList);
//        listView = (ListView) findViewById(R.id.list);

        String token = "123145644897";

        listListTask = new ShoppingListListTask(token);
        listListTask.execute();

        // TEMPORAIRE
        String[] listValues = new String[0];
        listValues[0] = listListTask.toString();
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
