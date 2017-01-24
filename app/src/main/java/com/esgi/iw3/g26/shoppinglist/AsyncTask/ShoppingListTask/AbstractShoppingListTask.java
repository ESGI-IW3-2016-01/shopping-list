package com.esgi.iw3.g26.shoppinglist.AsyncTask.ShoppingListTask;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.AbstractApiTask;

public abstract class AbstractShoppingListTask extends AbstractApiTask {

    protected final static String SHOPPING_LIST_CREATE_URL = "http://appspaces.fr/esgi/shopping_list/shopping_list/create.php";
    protected final static String SHOPPING_LIST_LIST_URL = "http://appspaces.fr/esgi/shopping_list/shopping_list/list.php";
    protected final static String SHOPPING_LIST_EDIT_URL = "http://appspaces.fr/esgi/shopping_list/shopping_list/edit.php";
    protected final static String SHOPPING_LIST_REMOVE_URL = "http://appspaces.fr/esgi/shopping_list/shopping_list/remove.php";

    public AbstractShoppingListTask(String token) {
        super(token);
    }
}
