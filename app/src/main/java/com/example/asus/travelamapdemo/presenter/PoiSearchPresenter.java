package com.example.asus.travelamapdemo.presenter;

import android.content.Context;

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

    public final static int FLAG_ENDPOINT_BY_TEAM = 1;
    public final static int FLAG_ENDPOINT_BY_INPUT = 2;

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
    public void GeocodeSearch(String name,String city) {
        poiSearchModel.doGeocodeQuery(name,city);
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
