package com.example.asus.travelamapdemo.util;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.asus.travelamapdemo.BasePresenter;

/**
 * Created by ASUS on 2017/7/23.
 */

public class PoiSearchUtil implements PoiSearch.OnPoiSearchListener{

    private static final int POISEARCH_QUERY_MARKER = 1;
    private static final int POISEARCH_QUERY_ENDPOINT = 2;
    private static final int POISEARCH_QUERY_SIGHTS = 3;
    private static final int POISEARCH_QUERY_NOTELOCATION = 4;

    public static final String AMAP_POI_CODE_TRAVEL = "110200";


    private PoiResult poiResult;

    public PoiSearchUtil(){
    }

    public void doSearchQuery(String str,String code,String city,int flag){
        PoiSearch.Query query;

    }

    public PoiResult getPoiResult() {
        return poiResult;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
