package com.example.mvp.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class HomeModelImpl implements IHomeModel {

    private Context context;
    private NetCallBack netCallBack;
    public HomeModelImpl(Context context,NetCallBack callBack){
        this.context = context;
        this.netCallBack = callBack;
    }

    @Override
    public void loadHomeData(String url, String page) {
        //发送网络请求 使用volley
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                netCallBack.onError(error.getMessage(),"404");
            }
        });
        queue.add(request);
    }
}
