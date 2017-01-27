package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListListTask;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListsActivity extends ListActivity implements IHttpRequestListener {

    ListView listView;
    private ShoppingListListTask listListTask = null;
    private String[] listValues = {};
    private UserSession session;
    private List<HashMap<String, String>> shoppinglistList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        this.session = new UserSession(getApplicationContext());
        String token = session.getToken();

        listListTask = new ShoppingListListTask(token);
        listListTask.execute();
    }


    @Override
    public void onSuccess(JSONObject object) {
        Log.d("activity:login:success", object.toString());
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

        String listValues[] = new String[shoppinglistList.size()];
        int i = 0;
        for (HashMap<String, String> hash : shoppinglistList) {
            for (String current : hash.values()) {
                listValues[i] = current;
                i++;
            }
        }

        listView = (ListView) findViewById(R.id.shopping_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValues));
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }
}
