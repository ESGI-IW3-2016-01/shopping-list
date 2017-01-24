package com.esgi.iw3.g26.shoppinglist.Interface;

import com.esgi.iw3.g26.shoppinglist.Entity.ResultCode;

import org.json.JSONObject;

public interface IHttpRequestListener {

    void onSuccess(JSONObject object);

    void onFailure(String message);

    void onApiError(JSONObject object);

}
