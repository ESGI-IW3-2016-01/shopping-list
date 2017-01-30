package com.esgi.iw3.g26.shoppinglist.Activity.Product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductEditTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductRemoveTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList.ListActivity;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class EditProductActivity extends AppCompatActivity implements IHttpRequestListener {

    EditText numberProduct, priceProduct, nameProduct;
    private String productId;
    private String productName;
    private String productPrice;
    private String productQuantity;

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
        productName = intent.getStringExtra(Product.PRODUCT_NAME_KEY);
        productPrice = intent.getStringExtra(Product.PRODUCT_PRICE_KEY);
        productQuantity = intent.getStringExtra(Product.PRODUCT_QUANTITY_KEY);

        nameProduct = (EditText) findViewById(R.id.editText);
        numberProduct = (EditText) findViewById(R.id.editNumberProduct);
        priceProduct = (EditText) findViewById(R.id.editPrice);

        nameProduct.setText(productName);
        numberProduct.setText(productQuantity);
        priceProduct.setText(productPrice);

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
        int quantity = TextUtils.isEmpty(numberProduct.getText().toString()) ? 1 : Integer.parseInt(numberProduct.getText().toString());
        double price = TextUtils.isEmpty(priceProduct.getText().toString()) ? 0.00 : Double.parseDouble(priceProduct.getText().toString());
        editProductTask = new ProductEditTask(session.getToken(), productId, price, nameProduct.getText().toString(), quantity);
        editProductTask.setListener(this);
        editProductTask.execute();
    }

    @Override
    public void onSuccess(JSONObject object) {
        Toast toast = Toast.makeText(getApplicationContext(), "Edit successful", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 10, 0);
        toast.show();
        this.redirectToShoppingListActivity();
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

    private void redirectToShoppingListActivity() {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        i.putExtras(getIntent().getExtras());
        startActivity(i);
        finish();
    }
}
