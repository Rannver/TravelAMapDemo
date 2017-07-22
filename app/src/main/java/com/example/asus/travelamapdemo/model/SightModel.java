package com.example.asus.travelamapdemo.model;

import com.example.asus.travelamapdemo.contract.SightContract;

/**
 * Created by ASUS on 2017/7/22.
 */

public class SightModel {

    private SightContract.SightPresenter sightPresenter;

    public SightModel(SightContract.SightPresenter sightPresenter){
        this.sightPresenter = sightPresenter;
    }

}
