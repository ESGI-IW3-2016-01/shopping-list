package com.esgi.iw3.g26.shoppinglist.Activity.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductEditTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductRemoveTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList.ListActivity;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class EditProductActivity extends Activity implements IHttpRequestListener {

    TextView nameListProduct;
    EditText numberProduct, priceProduct;
    private String productId;

    private ProductEditTask editProductTask;
    private ProductRemoveTask deleteProductTask;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);
        session = new UserSession(getApplicationContext());
        Intent intent = getIntent();
        productId = intent.getStringExtra(Product.PRODUCT_ID_KEY);

        nameListProduct = (TextView) findViewById(R.id.nameList);
        numberProduct = (EditText) findViewById(R.id.editNumberProduct);
        priceProduct = (EditText) findViewById(R.id.editPrice);

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        Button buttonDelete = (Button) findViewById(R.id.list_deleteProduct_button);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                executeDelete();
            }
        });

        Button buttonEdit = (Button) findViewById(R.id.list_saveProduct_button);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                executeEdit();
            }

        });
    }

    private void executeDelete() {
        deleteProductTask = new ProductRemoveTask(session.getToken(), productId);
        deleteProductTask.setListener(this);
        deleteProductTask.execute();
    }

    private void executeEdit() {
        String nameList = nameListProduct.getText().toString();
        int quantity = TextUtils.isEmpty(numberProduct.getText().toString()) ? 1 : Integer.parseInt(numberProduct.getText().toString());
        double price = TextUtils.isEmpty(priceProduct.getText().toString()) ? 0.00 : Double.parseDouble(priceProduct.getText().toString());
        editProductTask = new ProductEditTask(session.getToken(), productId, price, nameList, quantity);
        editProductTask.setListener(this);
        editProductTask.execute();
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.d("product:edit:success", object.toString());
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
        i.putExtras(getIntent().getExtras());
        startActivity(i);
    }
}
