package com.example.asus.travelamapdemo.util;

import com.amap.api.services.core.LatLonPoint;

/**
 * Created by ASUS on 2017/7/14.
 * 目前没什么作用
 */

public class LocationInfoSingleton{

    private LatLonPoint point;//搜索点定位
    private String name;//地点名字
    private String des;//地点详细描述
    private LatLonPoint  endPoint; //终点定位
    private String endName;//终点名字
    private String endDes;//终点描述

    private static LocationInfoSingleton infoSingleton = new LocationInfoSingleton();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public LatLonPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(LatLonPoint endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getEndDes() {
        return endDes;
    }

    public void setEndDes(String endDes) {
        this.endDes = endDes;
    }
}
