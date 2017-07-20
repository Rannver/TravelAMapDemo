package com.example.asus.travelamapdemo.model;

import com.example.asus.travelamapdemo.contract.RecommendContract;

/**
 * Created by ASUS on 2017/7/20.
 */

public class RecommendModel {

    private RecommendContract.RecPresenter presenter;

    public RecommendModel(RecommendContract.RecPresenter presenter){
        this.presenter = presenter;
    }
}
