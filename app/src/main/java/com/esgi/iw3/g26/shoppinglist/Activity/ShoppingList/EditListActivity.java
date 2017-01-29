package com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListEditTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListRemoveTask;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class EditListActivity extends AppCompatActivity implements IHttpRequestListener {

    // UI References
    private TextView textView;
    private Button button, buttonDelete;
    private String id;
    private String name;
    private Boolean completed;

    private ShoppingListEditTask editListTask;
    private ShoppingListRemoveTask deleteListTask = null;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);
        session = new UserSession(getApplicationContext());

        button = (Button) findViewById(R.id.create_list_button);
        buttonDelete = (Button) findViewById(R.id.list_deleteList_button);
        textView = (EditText) findViewById(R.id.nameList);

        Intent intent = getIntent();
        id = intent.getStringExtra(ShoppingList.SHOPPING_LIST_ID_KEY);
        name = intent.getStringExtra(ShoppingList.SHOPPING_LIST_NAME_KEY);
        textView.setText(name);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                executeDelete();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                executeCreate();
            }
        });
    }

    private void executeDelete() {
        deleteListTask = new ShoppingListRemoveTask(session.getToken(), id);
        deleteListTask.setListener(this);
        deleteListTask.execute();
    }

    private void executeCreate() {
        editListTask = new ShoppingListEditTask(session.getToken(), id, textView.getText().toString(), completed);
        editListTask.setListener(this);
        editListTask.execute();
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.i("activity:list:edit", object.toString());
        Intent i = new Intent(getApplicationContext(), ListsActivity.class);
        startActivity(i);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onApiError(JSONObject object) {
        Log.d("activity:list:edit", object.toString());
    }
}
