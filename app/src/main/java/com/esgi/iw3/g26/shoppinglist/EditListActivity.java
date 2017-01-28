package com.esgi.iw3.g26.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListCreateTask;
import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListEditTask;
import com.esgi.iw3.g26.shoppinglist.Entity.ShoppingList;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;

import org.json.JSONObject;

public class EditListActivity extends Activity implements IHttpRequestListener {

    // UI References
    private TextView textView;
    private Button button;
    private String id;
    private String name;
    private Boolean completed;

    private ShoppingListEditTask editListTask;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        session = new UserSession(getApplicationContext());
        button = (Button) findViewById(R.id.create_list_button);
        textView = (TextView) findViewById(R.id.nameList);

        Intent intent = getIntent();
        id = intent.getStringExtra(ShoppingList.SHOPPING_LIST_ID_KEY);
        name = intent.getStringExtra(ShoppingList.SHOPPING_LIST_NAME_KEY);
        completed = intent.getBooleanExtra(ShoppingList.SHOPPING_LIST_COMPLETED_KEY, true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                executeCreate();
            }
        });
    }

    private void executeCreate() {

        editListTask = new ShoppingListEditTask(session.getToken(), id, name, completed);
        editListTask.setListener(this);
        editListTask.execute();
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.i("activity:list:edit", object.toString());
        // Redirect to Shopping list Listing after creation
        // TODO : toast ?
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
