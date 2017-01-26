package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListCreateTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListListTask;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ListsActivity extends ListActivity implements IHttpRequestListener {

    TextView content;
    ListView listView;
    private ShoppingListListTask listListTask = null;
    private UserSession session;
    private List<HashMap<String, String>> shoppinglistList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        this.session = new UserSession(getApplicationContext());
//        content = (TextView)findViewById(R.id.nameList);
//        listView = (ListView) findViewById(R.id.list);

        String token = session.getToken();

        listListTask = new ShoppingListListTask(token);
        listListTask.execute();

        // TEMPORAIRE
//        String[] listValues = new String[0];
//        listValues[0] = listListTask.toString();
        // TEMPORAIRE


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValues);

//        setListAdapter(adapter);
    }


    @Override
    public void onSuccess(JSONObject object) {
        this.shoppinglistList.clear();
        try {
            JSONArray array = object.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject temp = (JSONObject) array.get(i);
                ShoppingList shoppingList = new ShoppingList(temp);
                shoppinglistList.add(shoppingList.toHashMap());
            }
        } catch (JSONException e) {

        }
        //TODO les données sont dans l'attribut il faut les charger dans l'adapter
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }
}
