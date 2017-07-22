package com.example.asus.travelamapdemo.presenter;

import com.example.asus.travelamapdemo.contract.SightContract;
import com.example.asus.travelamapdemo.model.SightModel;

/**
 * Created by ASUS on 2017/7/22.
 */

public class SightPresenter implements SightContract.SightPresenter {

    private SightModel sightModel;
    private SightContract.SightView sightView;

    public SightPresenter(SightContract.SightView sightView){
        this.sightView = sightView;
        sightModel = new SightModel(SightPresenter.this);
        this.sightView.setPresenter(SightPresenter.this);
    }

    @Override
    public void start() {
        sightView.initView();
    }
}
