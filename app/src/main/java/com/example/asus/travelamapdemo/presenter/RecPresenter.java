package com.example.asus.travelamapdemo.presenter;

import com.example.asus.travelamapdemo.contract.RecContract;
import com.example.asus.travelamapdemo.model.RecModel;

/**
 * Created by ASUS on 2017/7/22.
 */

public class RecPresenter implements RecContract.RecPresenter {

    private RecContract.RecView recView;
    private RecModel recModel;

    public RecPresenter(RecContract.RecView recView){
        this.recView = recView;
        recModel = new RecModel(RecPresenter.this);
        recView.setPresenter(RecPresenter.this);
    }

    @Override
    public void start() {
        recView.initView();
    }
}
