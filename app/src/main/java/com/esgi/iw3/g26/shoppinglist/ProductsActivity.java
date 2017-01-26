package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductListTask;
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

/**
 * Created by Jolan on 24/01/2017.
 */

public class ProductsActivity extends ListActivity implements IHttpRequestListener {

    TextView content;
    ListView listView;
    private ProductListTask listProductTask = null;
    private UserSession session;
    private List<HashMap<String, String>> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        this.session = new UserSession(getApplicationContext());
//        listView = (ListView) findViewById(R.id.list);

        String token = session.getToken();
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
        this.products.clear();
        try {
            JSONArray array = object.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                Product product = new Product(array.getJSONObject(i));
                products.add(product.toHashMap());
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
