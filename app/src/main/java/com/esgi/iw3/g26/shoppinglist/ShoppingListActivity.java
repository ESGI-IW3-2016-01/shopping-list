package com.esgi.iw3.g26.shoppinglist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jolan on 24/01/2017.
 */

public class ShoppingListActivity extends ListActivity {


    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



    }



}
