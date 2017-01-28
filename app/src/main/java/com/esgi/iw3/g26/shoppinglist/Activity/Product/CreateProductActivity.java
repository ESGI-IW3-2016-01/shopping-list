package com.esgi.iw3.g26.shoppinglist.Activity.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList.ListActivity;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductCreateTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class CreateProductActivity extends Activity implements IHttpRequestListener {

    TextView nameListProduct;
    Button button;
    EditText numberProduct, priceProduct;

    private String listId;
    private ProductCreateTask createProductTask = null;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        listId = intent.getStringExtra(ShoppingList.SHOPPING_LIST_ID_KEY);
        button = (Button) findViewById(R.id.list_saveProduct_button);
        addListenerOnButton();
        String[] shoppinglistsKey = new String[]{ShoppingList.SHOPPING_LIST_NAME_KEY, ShoppingList.SHOPPING_LIST_DATE_KEY, ShoppingList.SHOPPING_LIST_COMPLETED_KEY};
    }


    public void addListenerOnButton() {

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                nameListProduct = (TextView) findViewById(R.id.nameList);
                numberProduct = (EditText) findViewById(R.id.editNumberProduct);
                priceProduct = (EditText) findViewById(R.id.editPrice);

                String nameList = nameListProduct.getText().toString();

                int quantity = Integer.parseInt(numberProduct.getText().toString());
                double price = Double.parseDouble(priceProduct.getText().toString());

                session = new UserSession(getApplicationContext());
                String token = session.getToken();

                createProductTask = new ProductCreateTask(token, listId, nameList, quantity, price);
                createProductTask.execute();

            }

        });

    }


    @Override
    public void onSuccess(JSONObject object) {
        Log.d("activity:login:success", object.toString());

        Product objet = new Product(object);

        Product product = new Product(objet.getId(), objet.getName(), objet.getQuantity(), objet.getPrice());

        this.redirectToShoppingListActivity();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }

    private void redirectToShoppingListActivity() {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(i);
    }
}
