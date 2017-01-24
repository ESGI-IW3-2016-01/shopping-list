package com.esgi.iw3.g26.shoppinglist.AsyncTask;

public abstract class AbstractApiTask extends AbstractTask {

    protected final String token;

    protected  AbstractApiTask(String token) {
        this.token = token;
    }
}
