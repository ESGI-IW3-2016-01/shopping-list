package com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListListTask;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListsActivity extends Activity implements IHttpRequestListener {

    private ShoppingListListTask listListTask;
    private UserSession session;
    private ListView listView;
    private FloatingActionButton fab;
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

        fab = (FloatingActionButton) findViewById(R.id.shopping_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateListActivity.class);
                startActivity(i);
            }
        });

        simpleAdapter = new SimpleAdapter(this,
                shoppinglistList,
                android.R.layout.simple_list_item_2,
                new String[]{ShoppingList.SHOPPING_LIST_TEXT_1, ShoppingList.SHOPPING_LIST_TEXT_2},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                HashMap<String, String> map = (HashMap<String, String>) o;
                redirectToShoppingList(map);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                HashMap<String, String> map = (HashMap<String, String>) o;
                redirectToShoppingEditList(map);
                return true;
            }
        });
    }

    public void redirectToShoppingList(HashMap<String, String> map) {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        i.putExtra(ShoppingList.SHOPPING_LIST_ID_KEY, map.get(ShoppingList.SHOPPING_LIST_ID_KEY));
        i.putExtra(ShoppingList.SHOPPING_LIST_NAME_KEY, map.get(ShoppingList.SHOPPING_LIST_NAME_KEY));
        i.putExtra(ShoppingList.SHOPPING_LIST_DATE_KEY, map.get(ShoppingList.SHOPPING_LIST_DATE_KEY));
        i.putExtra(ShoppingList.SHOPPING_LIST_COMPLETED_KEY, map.get(ShoppingList.SHOPPING_LIST_COMPLETED_KEY));
        startActivity(i);
    }

    public void redirectToShoppingEditList(HashMap<String, String> map) {
        Intent i = new Intent(getApplicationContext(), EditListActivity.class);
        i.putExtra(ShoppingList.SHOPPING_LIST_ID_KEY, map.get(ShoppingList.SHOPPING_LIST_ID_KEY));
        i.putExtra(ShoppingList.SHOPPING_LIST_NAME_KEY, map.get(ShoppingList.SHOPPING_LIST_NAME_KEY));
        startActivity(i);
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.d("activity:login:success", object.toString());
        this.shoppinglistList.clear();

        try {
            JSONArray array = object.getJSONArray("result");
            if (array.length() <= 0) {
//                TODO no shopping list yet
            } else {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject temp = (JSONObject) array.get(i);
                    ShoppingList shoppingList = new ShoppingList(temp);
                    shoppinglistList.add(shoppingList.toHashMap());
                }
//                Sort shopping list
                Collections.reverse(shoppinglistList);
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
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
