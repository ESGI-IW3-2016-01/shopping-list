package com.esgi.iw3.g26.shoppinglist.AsyncTask.UserTask;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.AbstractApiTask;

/**
 * Created by antoine on 23/01/2017.
 */
public abstract class AbstractUserTask extends AbstractApiTask {

    protected final static String USER_LOGIN_URL = "http://appspaces.fr/esgi/shopping_list/account/login.php";
    protected final static String USER_SUBSCRIBE_URL = "http://appspaces.fr/esgi/shopping_list/account/subscribe.php";

    protected String mEmail;
    protected String mPassword;

    protected AbstractUserTask(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }
}
