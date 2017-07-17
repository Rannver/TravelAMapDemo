package com.example.asus.travelamapdemo.presenter;


import com.amap.api.services.core.LatLonPoint;
import com.example.asus.travelamapdemo.contract.MapContract;
import com.example.asus.travelamapdemo.model.MapModel;

import static android.R.attr.name;

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
        mapView.initView();
        mapView.initMap();
    }

    @Override
    public void setSearchMarkerPoint(LatLonPoint point, String name, String des) {
        mapView.initMarkerBySearch(point,name,des);
    }


    @Override
    public void start() {
        initView();
    }
}
