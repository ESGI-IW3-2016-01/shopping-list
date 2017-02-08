package com.esgi.iw3.g26.shoppinglist.Activity.Product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList.ListActivity;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductCreateTask;
import com.esgi.iw3.g26.shoppinglist.Entity.Product;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class CreateProductActivity extends Activity implements IHttpRequestListener {

    private TextView nameListProduct;
    private Button button;
    private EditText numberProduct, priceProduct;

    private String listId;
    private ProductCreateTask createProductTask = null;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        session = new UserSession(getApplicationContext());

        Intent intent = getIntent();
        listId = intent.getStringExtra(ShoppingList.SHOPPING_LIST_ID_KEY);

        button = (Button) findViewById(R.id.list_saveProduct_button);
        nameListProduct = (TextView) findViewById(R.id.editText);
        numberProduct = (EditText) findViewById(R.id.editNumberProduct);
        priceProduct = (EditText) findViewById(R.id.editPrice);

        addListenerOnButton();
    }


    private void addListenerOnButton() {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                executeCreate();
            }

        });
    }

    private void executeCreate() {
        int quantity = TextUtils.isEmpty(numberProduct.getText().toString()) ? 1 : Integer.parseInt(numberProduct.getText().toString());
        double price = TextUtils.isEmpty(priceProduct.getText().toString()) ? 0.00 : Double.parseDouble(priceProduct.getText().toString());
        createProductTask = new ProductCreateTask(session.getToken(),
                listId,
                nameListProduct.getText().toString(),
                quantity,
                price);
        createProductTask.setListener(this);
        createProductTask.execute();
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.d("product:create:success", object.toString());
        Product product = new Product(object);
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
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show();
    }

    private void redirectToShoppingListActivity() {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        i.putExtras(getIntent().getExtras());
        startActivity(i);
        finish();
    }
}
