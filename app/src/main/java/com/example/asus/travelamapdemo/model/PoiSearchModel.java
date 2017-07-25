package com.example.asus.travelamapdemo.model;

import android.widget.Toast;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.asus.travelamapdemo.contract.PoiSearchContract;
import com.example.asus.travelamapdemo.util.PoiSearchUtil;
import com.example.asus.travelamapdemo.view.activity.PoiSearchActivity;

import static com.example.asus.travelamapdemo.util.PoiSearchUtil.POISEARCH_QUERY_ENDPOINT;

/**
 * Created by ASUS on 2017/7/13.
 */

public class PoiSearchModel implements GeocodeSearch.OnGeocodeSearchListener{

    private PoiSearchContract.PoiSearchPresenter poiSearchPresenter;

    private static String TAG = "PoiSearchModel";

    public PoiSearchModel(PoiSearchContract.PoiSearchPresenter poiSearchPresenter){
        this.poiSearchPresenter = poiSearchPresenter;
    }

    public void doSearchQuery(String str,int flag){
        PoiSearchUtil poiSearchUtil = new PoiSearchUtil(poiSearchPresenter.getView(),poiSearchPresenter);
        switch (flag){
            case PoiSearchActivity.FLAG_INTENT_BY_MAP:
                poiSearchUtil.doSearchQuery(str,"","",PoiSearchUtil.POISEARCH_QUERY_MARKER);
                break;
            case PoiSearchActivity.FLAG_INTENT_BY_TEAM:
                poiSearchUtil.doSearchQuery(str,PoiSearchUtil.AMAP_POI_CODE_TRAVEL,"",POISEARCH_QUERY_ENDPOINT);
                break;
        }
    }

    public void doGeocodeQuery(String name,String city){
        GeocodeSearch geocodeSearch = new GeocodeSearch(poiSearchPresenter.getView());
        geocodeSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(name,city);//查询名称，城市名称
        geocodeSearch.getFromLocationNameAsyn(query);

    }


    /**
     *逆地理编码回调（目前不需要这个函数）
     */
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if(i==AMapException.CODE_AMAP_SUCCESS){
            if(geocodeResult!=null&&geocodeResult.getGeocodeAddressList()!=null&&geocodeResult.getGeocodeAddressList().size()>0){
//                System.out.println(TAG+":"+geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint());
                poiSearchPresenter.GeocodeSearchOK(geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint());
            }
        }
    }

    /**
     * 地理编码查询回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {


    }
}
