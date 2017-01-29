package com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.esgi.iw3.g26.shoppinglist.Activity.Product.CreateProductActivity;
import com.esgi.iw3.g26.shoppinglist.Activity.Product.EditProductActivity;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductListTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
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

public class ListActivity extends AppCompatActivity implements IHttpRequestListener {

    private ProductListTask productListTask;
    private UserSession session;
    private List<HashMap<String, String>> shoppingProductList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private String listId;
    private Intent fromIntent;
    private double total;
    // UI References
    private Button button;
    private FloatingActionButton fab;
    private FloatingActionButton deleteFab;
    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        this.session = new UserSession(getApplicationContext());

        fromIntent = getIntent();
        String listName = fromIntent.getStringExtra(ShoppingList.SHOPPING_LIST_NAME_KEY);
        String listDate = fromIntent.getStringExtra(ShoppingList.SHOPPING_LIST_DATE_KEY);
        String isActive = fromIntent.getStringExtra(ShoppingList.SHOPPING_LIST_COMPLETED_KEY);
        listId = fromIntent.getStringExtra(ShoppingList.SHOPPING_LIST_ID_KEY);

        if (isActive.equals("1")) {
            isActive = "Active";
        } else {
            isActive = "Completed";
        }

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(listName + " " + listDate + " " + isActive + " " + listId + " total : " + total);

        listView = (ListView) findViewById(android.R.id.list);
        productListTask = new ProductListTask(session.getToken(), listId);
        productListTask.setListener(this);
        productListTask.execute();

        fab = (FloatingActionButton) findViewById(R.id.product_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to create product page
                Intent i = new Intent(getApplicationContext(), CreateProductActivity.class);
                i.putExtras(fromIntent.getExtras());
                startActivity(i);
            }
        });

        simpleAdapter = new SimpleAdapter(this,
                shoppingProductList,
                android.R.layout.simple_list_item_2,
                new String[]{Product.PRODUCT_LIST_TEXT_1, Product.PRODUCT_LIST_TEXT_2},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                HashMap<String, String> map = (HashMap<String, String>) o;
                redirectToEditProduct(map);
            }
        });
    }

    public void redirectToEditProduct(HashMap<String, String> map) {
        Intent i = new Intent(getApplicationContext(), EditProductActivity.class);
        i.putExtra(Product.PRODUCT_ID_KEY, map.get(Product.PRODUCT_ID_KEY));
        i.putExtra(Product.PRODUCT_NAME_KEY, map.get(Product.PRODUCT_NAME_KEY));
        i.putExtra(Product.PRODUCT_QUANTITY_KEY, map.get(Product.PRODUCT_QUANTITY_KEY));
        i.putExtra(Product.PRODUCT_PRICE_KEY, map.get(Product.PRODUCT_PRICE_KEY));
        startActivity(i);
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.i("activity:List:success", object.toString());
        this.shoppingProductList.clear();
        this.total = 0;
        try {
            JSONArray array = object.getJSONArray("result");
            if (array.length() <= 0) {
//                TODO no shopping list yet
            } else {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject temp = (JSONObject) array.get(i);
                    Product product = new Product(temp);
                    shoppingProductList.add(product.toHashMap());
                    total += (product.getQuantity() * product.getPrice());
                }
//                Sort shopping list
                Collections.reverse(shoppingProductList);
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
        CharSequence text = object.optString("msg");
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
