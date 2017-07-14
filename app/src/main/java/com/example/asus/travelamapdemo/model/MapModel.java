package com.example.asus.travelamapdemo.model;


import com.example.asus.travelamapdemo.contract.MapContract;

/**
 * Created by ASUS on 2017/7/10.
 */

public class MapModel {

    private MapContract.MapPresenter mapPresenter;

    public MapModel(MapContract.MapPresenter mapPresenter){
        this.mapPresenter = mapPresenter;
    }
}
