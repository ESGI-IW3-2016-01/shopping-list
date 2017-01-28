package com.esgi.iw3.g26.shoppinglist.Activity.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductEditTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductRemoveTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList.ListActivity;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class EditProductActivity extends Activity implements IHttpRequestListener {

    TextView nameListProduct;
    EditText numberProduct, priceProduct;
    private String productId;

    private ProductEditTask editProductTask = null;
    private ProductRemoveTask deleteProductTask = null;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        Intent intent = getIntent();
        productId = intent.getStringExtra(Product.PRODUCT_ID_KEY);

        addListenerOnButton();
        String[] shoppinglistsKey = new String[]{ShoppingList.SHOPPING_LIST_NAME_KEY,ShoppingList.SHOPPING_LIST_DATE_KEY,ShoppingList.SHOPPING_LIST_COMPLETED_KEY};
    }


    public void addListenerOnButton() {


        Button buttonDelete= (Button) findViewById(R.id.list_deleteProduct_button);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                session = new UserSession(getApplicationContext());
                String token = session.getToken();
                deleteProductTask = new ProductRemoveTask(token, productId);
                deleteProductTask.execute();
            }
        });



        Button buttonEdit= (Button) findViewById(R.id.list_saveProduct_button);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                nameListProduct = (TextView)findViewById(R.id.nameList);
                numberProduct = (EditText)findViewById(R.id.editNumberProduct);
                priceProduct = (EditText)findViewById(R.id.editPrice);

                String nameList = nameListProduct.getText().toString();

                int quantity = Integer.parseInt(numberProduct.getText().toString());
                double price = Double.parseDouble(priceProduct.getText().toString());

                session = new UserSession(getApplicationContext());
                String token = session.getToken();

                editProductTask = new ProductEditTask(token, productId,  price, nameList, quantity);
                editProductTask.execute();

            }

        });

    }


    @Override
    public void onSuccess(JSONObject object) {
        Log.d("activity:edit:success", object.toString());

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
