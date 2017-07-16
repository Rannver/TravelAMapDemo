package com.example.asus.travelamapdemo.contract;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

import java.util.List;

/**
 * Created by ASUS on 2017/7/13.
 */

public interface PoiSearchContract {

    interface PoiSearchView extends BaseView<PoiSearchPresenter>{

        void initView();//设置搜索框等页面属性

        void poiSearch();//获取搜索框内容进行poi搜索

        void initPoiList(List<PoiItem> list);//显示搜索结果

        void AmapIntent(LatLonPoint point);//跳转至地图界面

        Context getContext();

    }

    interface PoiSearchPresenter extends BasePresenter{

        void init();//通知设置界面

        void poiSearch(String str);//通知进行poi搜索

        void GeocodeSearch(String name, String city);//通知进行Geocode搜索

        void GeocodeSearchOK(LatLonPoint point);//geocodeSearch搜索成功

        void setList(List<PoiItem> list);//设置poi搜索结果列表

        Context getView();

    }
}
