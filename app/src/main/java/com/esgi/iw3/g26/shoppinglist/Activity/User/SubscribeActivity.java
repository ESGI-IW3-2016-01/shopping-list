package com.esgi.iw3.g26.shoppinglist.Activity.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.UserTask.UserSubscribeTask;
import com.esgi.iw3.g26.shoppinglist.Entity.User;
import com.esgi.iw3.g26.shoppinglist.Interface.IHttpRequestListener;
import com.esgi.iw3.g26.shoppinglist.Activity.ShoppingList.ListsActivity;
import com.esgi.iw3.g26.shoppinglist.R;
import com.esgi.iw3.g26.shoppinglist.UserSession;

import org.json.JSONObject;

public class SubscribeActivity extends Activity implements IHttpRequestListener {

    TextView content;
    ListView listView;
    private UserSubscribeTask userSubscribeTask;
    private EditText mPasswordView;
    private EditText mFirstNameView;
    private EditText mLastNameView;
    private AutoCompleteTextView mEmailview;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        this.session = new UserSession(getApplicationContext());

        Button subscribeButton = (Button) findViewById(R.id.email_subscribe_in_button);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptConnect();
            }
        });
    }

    private void attemptConnect() {
        mEmailview = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mFirstNameView = (EditText) findViewById(R.id.firstName);
        mLastNameView = (EditText) findViewById(R.id.lastName);

        String email = mEmailview.getText().toString();
        String password = mPasswordView.getText().toString();
        String firstName = mFirstNameView.getText().toString();
        String lastName = mLastNameView.getText().toString();

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_empty_password));
        }
        if (TextUtils.isEmpty(firstName)) {
            mFirstNameView.setError(getString(R.string.error_empty_password));
        }

        if (!isEmailValid(email)) {
            mEmailview.setError(getString(R.string.error_invalid_email));
        }

        userSubscribeTask = new UserSubscribeTask(email, password, firstName, lastName);
        userSubscribeTask.setListener(this);
        userSubscribeTask.execute();
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //return email.contains("@");
        return true;
    }

    @Override
    public void onSuccess(JSONObject object) {
        User user = new User(object);
        session.createUserLoginSession(user);
        this.redirectToShoppingListActivity();
    }

    @Override
    public void onFailure(String message) {
    }

    @Override
    public void onApiError(JSONObject object) {
        Log.d("activity:lists:api", object.optString("msg"));

        CharSequence text = object.optString("msg");
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, 3);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    private void redirectToShoppingListActivity() {
        Intent i = new Intent(getApplicationContext(), ListsActivity.class);
        startActivity(i);
    }
}
