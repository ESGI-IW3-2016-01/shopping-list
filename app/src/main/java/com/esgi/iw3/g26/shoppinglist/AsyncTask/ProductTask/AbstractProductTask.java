package com.esgi.iw3.g26.shoppinglist.AsyncTask.ProductTask;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.AbstractApiTask;

public abstract class AbstractProductTask extends AbstractApiTask {

    protected final static String PRODUCT_CREATE_URL = "http://appspaces.fr/esgi/shopping_list/product/create.php";
    protected final static String PRODUCT_LIST_URL = "http://appspaces.fr/esgi/shopping_list/product/list.php";
    protected final static String PRODUCT_EDIT_URL = "http://appspaces.fr/esgi/shopping_list/product/edit.php";
    protected final static String PRODUCT_REMOVE_URL = "http://appspaces.fr/esgi/shopping_list/product/remove.php";

    public AbstractProductTask(String token) {
        super(token);
    }
}
