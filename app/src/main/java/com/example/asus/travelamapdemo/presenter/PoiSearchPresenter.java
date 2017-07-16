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
    public void poiSearch(String str) {
        poiSearchModel.doSearchQuery(str);
    }

    @Override
    public void GeocodeSearch(String name, String city) {
        poiSearchModel.doGeocodeQuery(name,city);
    }

    @Override
    public void GeocodeSearchOK(LatLonPoint point) {
        poiSearchView.AmapIntent(point);
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
