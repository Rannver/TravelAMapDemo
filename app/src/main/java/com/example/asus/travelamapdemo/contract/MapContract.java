package com.example.asus.travelamapdemo.contract;


import com.amap.api.maps2d.model.LatLng;
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

        void initMarkerBySearch(LatLonPoint point,String name,String des);//设置手动添加的Marker

        void setMarkerOnclickListener(LatLng latLng,String name,String des);
    }

    interface MapPresenter extends BasePresenter {

        void initView();

        void setSearchMarkerPoint(LatLonPoint point,String name,String des);

        void dosearchResult(LatLonPoint start,LatLonPoint end);
    }
}
