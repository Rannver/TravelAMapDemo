package com.example.asus.travelamapdemo.presenter;

import com.example.asus.travelamapdemo.contract.RecommendContract;
import com.example.asus.travelamapdemo.model.RecommendModel;

/**
 * Created by ASUS on 2017/7/20.
 */

public class RecommendPresenter implements RecommendContract.RecPresenter {

    private RecommendContract.RecView recView;
    private RecommendModel recModel;

    public RecommendPresenter(RecommendContract.RecView recView){
        this.recView = recView;
        recModel = new RecommendModel(RecommendPresenter.this);
        this.recView.setPresenter(RecommendPresenter.this);
    }

    @Override
    public void start() {

    }
}
