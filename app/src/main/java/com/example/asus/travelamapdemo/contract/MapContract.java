package com.example.asus.travelamapdemo.contract;


import android.content.Context;
import android.view.ViewGroup;

import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
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

        void showDrivePathList(DriveRouteResult result,boolean isEnd);//显示驾车路径列表

        void showPathLine(LatLonPoint start,LatLonPoint end,DrivePath path,boolean isEnd);//显示选择路线

        void CancelPathLine();//取消路线显示

        Context getMapContext();

        ViewGroup getViewGroup();
    }

    interface MapPresenter extends BasePresenter {

        void initView();

        void setSearchMarkerPoint(LatLonPoint point,String name,String des);

        void dosearchResult(LatLonPoint start,LatLonPoint end,boolean isEnd);

        void setPathList(DriveRouteResult result,boolean isEnd);

        void setPathLine(LatLonPoint start,LatLonPoint end,DrivePath path,boolean isEnd);//显示选择路线

        void CancelPathLine();//取消路线显示

        Context getContext();

        ViewGroup getViewGroup();
    }
}
