package com.esgi.iw3.g26.shoppinglist.AsyncTask.UserTask;

import com.esgi.iw3.g26.shoppinglist.AsyncTask.AbstractTask;

public abstract class AbstractUserTask extends AbstractTask {

    protected final static String USER_LOGIN_URL = "http://appspaces.fr/esgi/shopping_list/account/login.php";
    protected final static String USER_SUBSCRIBE_URL = "http://appspaces.fr/esgi/shopping_list/account/subscribe.php";

    protected String mEmail;
    protected String mPassword;

    protected AbstractUserTask(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }
}
