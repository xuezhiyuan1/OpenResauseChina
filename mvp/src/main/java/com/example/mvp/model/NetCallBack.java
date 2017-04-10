package com.example.mvp.model;


public interface NetCallBack {

    void onSuccess(String jsonData);
    void onError(String msg, String errorCode);
}
