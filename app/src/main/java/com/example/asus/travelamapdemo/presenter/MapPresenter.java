package com.example.asus.travelamapdemo.presenter;


import android.content.Context;
import android.view.ViewGroup;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.example.asus.travelamapdemo.contract.MapContract;
import com.example.asus.travelamapdemo.model.MapModel;

import java.util.List;

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
        mapView.initEndPoint();
    }

    @Override
    public void setSearchMarkerPoint(LatLonPoint point, String name, String des) {
        mapView.initMarkerBySearch(point,name,des);
    }

    @Override
    public void dosearchResult(LatLonPoint start, LatLonPoint end,boolean isEnd) {
        mapModel.DriveRouteQuery(start,end,isEnd);
    }

    @Override
    public void setPathList(DriveRouteResult result,boolean isEnd) {
        mapView.showDrivePathList(result,isEnd);
    }

    @Override
    public void setPathLine(LatLonPoint start, LatLonPoint end, DrivePath path,boolean isEnd) {
        mapView.showPathLine(start,end,path,isEnd);
    }

    @Override
    public void CancelPathLine() {
        mapView.CancelPathLine();
    }

    @Override
    public Context getContext() {
        return mapView.getMapContext();
    }

    @Override
    public ViewGroup getViewGroup() {
        return mapView.getViewGroup();
    }

    @Override
    public void start() {
        initView();
    }
}
