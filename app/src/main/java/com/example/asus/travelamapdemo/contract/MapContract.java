package com.example.asus.travelamapdemo.contract;


import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/10.
 */

public interface MapContract {

    interface MapView extends BaseView<MapPresenter> {

        void initMap();//设置地图和当前位置

        void initEndPoint();//设置终点Marker
    }

    interface MapPresenter extends BasePresenter {
        void initView();
    }
}
