package com.esgi.iw3.g26.shoppinglist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductCreateTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ProductActivity extends Activity implements IHttpRequestListener {

    TextView textView;
    Button button;
    EditText editText1, editText2;

    private ProductCreateTask createProductTask = null;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        addListenerOnButton();
        String[] shoppinglistsKey = new String[]{ShoppingList.SHOPPING_LIST_NAME_KEY,ShoppingList.SHOPPING_LIST_DATE_KEY,ShoppingList.SHOPPING_LIST_COMPLETED_KEY};
    }


    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.list_saveList_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                textView = (TextView)findViewById(R.id.nameList);
                editText1 = (EditText)findViewById(R.id.editNumberProduct);
                editText2 = (EditText)findViewById(R.id.editPrice);

                String nameList = textView.getText().toString();

                int quantity = Integer.parseInt(editText1.getText().toString());
                double price = Double.parseDouble(editText1.getText().toString());


                session = new UserSession(getApplicationContext());
                String token = session.getToken();

                String listId = "123";
                createProductTask = new ProductCreateTask(token, listId,  nameList, quantity, price);
                createProductTask.execute();

            }

        });

    }


    @Override
    public void onSuccess(JSONObject object) {
        Log.d("activity:login:success", object.toString());

        Product objet = new Product(object);

        Product product = new Product( objet.getId(), objet.getName(), objet.getQuantity(), objet.getPrice());

        this.redirectToShoppingListActivity();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }

    private void redirectToShoppingListActivity() {
        Intent i = new Intent(getApplicationContext(), com.esgi.iw3.g26.shoppinglist.ListActivity.class);
        startActivity(i);
    }
}
