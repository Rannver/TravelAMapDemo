package com.example.asus.travelamapdemo.contract;


import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/10.
 */

public interface MapContract {

    interface MapView extends BaseView<MapPresenter> {
        void initMap();
    }

    interface MapPresenter extends BasePresenter {
        void initView();
    }
}
