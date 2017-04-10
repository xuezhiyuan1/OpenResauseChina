package com.example.mvp.presenter;

import android.content.Context;

import com.example.mvp.model.entity.Goods;
import com.example.mvp.model.HomeModelImpl;
import com.example.mvp.model.IHomeModel;
import com.example.mvp.model.NetCallBack;
import com.example.mvp.view.IHomeView;
import com.google.gson.Gson;

import java.util.List;


public class HomePresenter implements IHomePresenter,NetCallBack {

    private IHomeModel homeModel;
    private IHomeView homeView;
    public HomePresenter(Context context){
        homeModel = new HomeModelImpl(context,this);
        if(context instanceof IHomeView)
            homeView = (IHomeView) context;
    }

    @Override
    public void loadDatas(String url, String page) {

        homeModel.loadHomeData(url,page);
    }

    @Override
    public void loadData(String url) {

    }

    @Override
    public void onSuccess(String jsonData) {

        Gson gson = new Gson();
        Goods goods = gson.fromJson(jsonData, Goods.class);
        List<Goods.DataBean> data = goods.getData();
        homeView.addData(data);

    }

    @Override
    public void onError(String msg, String errorCode) {

    }
}
