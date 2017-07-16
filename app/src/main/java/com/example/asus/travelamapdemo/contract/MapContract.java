package com.example.asus.travelamapdemo.contract;


import com.amap.api.services.core.LatLonPoint;
import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/10.
 */

public interface MapContract {

    interface MapView extends BaseView<MapPresenter> {

        void initView();

        void initMap();//设置地图和当前位置

        void initEndPoint();//设置终点Marker

        void initMarkerBySearch(LatLonPoint point);//设置手动添加的Marker
    }

    interface MapPresenter extends BasePresenter {

        void initView();

        void setSearchMarkerPoint(LatLonPoint point);
    }
}
