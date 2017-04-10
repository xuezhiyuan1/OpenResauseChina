package com.example.mvp.view;

import com.example.mvp.model.entity.Goods;

import java.util.List;

public interface IHomeView {


    void showProgressBar();

    //往listview中添加数据
    void addData(List<Goods.DataBean> data);

    void closeProgressBar();

    //Toast信息
    void showFaildMsg();


}