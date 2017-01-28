package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListListTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListsActivity extends ListActivity implements IHttpRequestListener {

    private ShoppingListListTask listListTask;
    private UserSession session;
    private ListView listView;
    private List<HashMap<String, String>> shoppinglistList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        this.session = new UserSession(getApplicationContext());
        listView = (ListView) findViewById(android.R.id.list);
        listListTask = new ShoppingListListTask(session.getToken());
        listListTask.setListener(this);
        listListTask.execute();

        simpleAdapter = new SimpleAdapter(this,
                shoppinglistList,
                android.R.layout.simple_selectable_list_item,
                new String[]{ShoppingList.SHOPPING_LIST_NAME_KEY, ShoppingList.SHOPPING_LIST_DATE_KEY},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
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
        simpleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {
        Log.d("activity:lists:api", object.optString("msg"));

        CharSequence text = object.optString("msg");
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, 3);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
    }
}
