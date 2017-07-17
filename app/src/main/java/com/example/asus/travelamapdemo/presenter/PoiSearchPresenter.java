package com.example.asus.travelamapdemo.presenter;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.asus.travelamapdemo.contract.PoiSearchContract;
import com.example.asus.travelamapdemo.model.PoiSearchModel;

import java.util.List;

/**
 * Created by ASUS on 2017/7/13.
 */

public class PoiSearchPresenter implements PoiSearchContract.PoiSearchPresenter {

    private PoiSearchModel poiSearchModel;
    private PoiSearchContract.PoiSearchView poiSearchView;

    private List<PoiItem> poilist;
    private PoiItem poiItem;
    private int flag;

    public PoiSearchPresenter(PoiSearchContract.PoiSearchView poiSearchView){
        this.poiSearchView = poiSearchView;
        poiSearchModel = new PoiSearchModel(this);
        this.poiSearchView.setPresenter(this);
    }

    @Override
    public void start() {
        init();
    }

    @Override
    public void init() {
        poiSearchView.initView();
    }

    @Override
    public void poiSearch(String str,int flag) {
        poiSearchModel.doSearchQuery(str,flag);
    }

    @Override
    public void GeocodeSearch(PoiItem poiItem) {
        poiSearchModel.doGeocodeQuery(poiItem.getTitle(),poiItem.getCityName());
        this.poiItem = poiItem;
    }

    @Override
    public void GeocodeSearchOK(LatLonPoint point) {
        poiSearchView.AmapIntent(point,poiItem);
    }

    @Override
    public void setList(List<PoiItem> list) {
        poiSearchView.initPoiList(list);
    }

    @Override
    public Context getView() {
        return poiSearchView.getContext();
    }

}
