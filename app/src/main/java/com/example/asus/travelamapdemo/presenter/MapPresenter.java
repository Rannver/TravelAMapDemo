package com.example.asus.travelamapdemo.presenter;


import com.example.asus.travelamapdemo.contract.MapContract;
import com.example.asus.travelamapdemo.model.MapModel;

/**
 * Created by ASUS on 2017/7/10.
 */

public class MapPresenter implements MapContract.MapPresenter{

    private MapModel mapModel;
    private MapContract.MapView mapView;

    public MapPresenter(MapContract.MapView mapView){
        this.mapView = mapView;
        mapModel = new MapModel(MapPresenter.this);
        this.mapView.setPresenter(MapPresenter.this);
    }

    @Override
    public void initView() {
       // mapView.initMap();
    }

    @Override
    public void start() {
        initView();
    }
}
