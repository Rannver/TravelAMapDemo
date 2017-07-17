package com.example.asus.travelamapdemo.contract;


import android.content.Context;

import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

import java.util.List;

/**
 * Created by ASUS on 2017/7/10.
 */

public interface MapContract {

    interface MapView extends BaseView<MapPresenter> {

        void initView();

        void initMap();//设置地图和当前位置

        void initEndPoint();//设置终点Marker

        void initMarkerBySearch(LatLonPoint point,String name,String des);//设置手动添加的Marker

        void setMarkerOnclickListener(LatLng latLng,String name,String des);

        void showDrivePathList(List<DrivePath> list);//显示驾车路径列表

        Context getMapContext();
    }

    interface MapPresenter extends BasePresenter {

        void initView();

        void setSearchMarkerPoint(LatLonPoint point,String name,String des);

        void dosearchResult(LatLonPoint start,LatLonPoint end);

        void setPathList(List<DrivePath> list);

        Context getContext();
    }
}
