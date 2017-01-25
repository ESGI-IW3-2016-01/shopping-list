package com.esgi.iw3.g26.shoppinglist;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask.ProductCreateTask;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        addListenerOnButton();

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


                String token = "123145644897";
                String listId = "123";
                createProductTask = new ProductCreateTask(token, listId,  nameList, quantity, price);
                createProductTask.execute();

            }

        });

    }


    @Override
    public void onSuccess(JSONObject object) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {

    }
}
