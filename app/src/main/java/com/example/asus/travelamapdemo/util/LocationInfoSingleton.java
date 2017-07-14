package com.example.asus.travelamapdemo.util;

import com.amap.api.services.core.LatLonPoint;

/**
 * Created by ASUS on 2017/7/14.
 * 目前没什么作用
 */

public class LocationInfoSingleton{

    private static LocationInfoSingleton infoSingleton = new LocationInfoSingleton();

    private LatLonPoint point;

    private LocationInfoSingleton(){
        System.out.println("LocationInfoSingleton create");
    }

    public static LocationInfoSingleton getInfoSingleton(){
        return infoSingleton;
    }

    public void setPoint(LatLonPoint point){
        this.point = point;
    }

    public LatLonPoint getPoint(){
        return point;
    }
}
