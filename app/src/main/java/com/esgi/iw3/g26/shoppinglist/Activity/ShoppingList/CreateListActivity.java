package com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask.ShoppingListCreateTask;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class CreateListActivity extends AppCompatActivity implements IHttpRequestListener {

    // UI References
    private TextView textView;
    private Button button;

    private ShoppingListCreateTask createListTask;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        session = new UserSession(getApplicationContext());
        button = (Button) findViewById(R.id.create_list_button);
        textView = (TextView) findViewById(R.id.nameList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                executeCreate();
            }
        });
    }

    private void executeCreate() {
        createListTask = new ShoppingListCreateTask(session.getToken(), textView.getText().toString());
        createListTask.setListener(this);
        createListTask.execute();
    }

    @Override
    public void onSuccess(JSONObject object) {
        Log.i("activity:list:create", object.toString());
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
        Log.d("activity:list:create", object.toString());
    }
}
