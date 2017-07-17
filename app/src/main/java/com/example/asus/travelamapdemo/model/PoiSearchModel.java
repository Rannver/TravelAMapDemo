package com.example.asus.travelamapdemo.model;

import android.widget.Toast;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.asus.travelamapdemo.contract.PoiSearchContract;
import com.example.asus.travelamapdemo.presenter.PoiSearchPresenter;
import com.example.asus.travelamapdemo.util.LocationInfoSingleton;
import com.example.asus.travelamapdemo.view.activity.PoiSearchActivity;

/**
 * Created by ASUS on 2017/7/13.
 */

public class PoiSearchModel implements PoiSearch.OnPoiSearchListener,GeocodeSearch.OnGeocodeSearchListener{

    private PoiSearchContract.PoiSearchPresenter poiSearchPresenter;

    private static String AMAP_POI_CODE_TRAVEL = "110200";
    private static String TAG = "PoiSearchModel";

    public PoiSearchModel(PoiSearchContract.PoiSearchPresenter poiSearchPresenter){
        this.poiSearchPresenter = poiSearchPresenter;
    }

    public void doSearchQuery(String str,int flag){
        PoiSearch.Query query = null;
        switch (flag){
            case PoiSearchActivity.FLAG_INTENT_BY_MAP:
                query = new PoiSearch.Query(str,"","");//地址搜索，第三参数默认为空，全国范围内搜索
                break;
            case PoiSearchActivity.FLAG_INTENT_BY_TEAM:
                query = new PoiSearch.Query(str,AMAP_POI_CODE_TRAVEL,"");//景区搜索第三参数默认为空，全国范围内搜索
                break;
        }
        PoiSearch poiSearch = new PoiSearch(poiSearchPresenter.getView(),query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    public void doGeocodeQuery(String name,String city){
        GeocodeSearch geocodeSearch = new GeocodeSearch(poiSearchPresenter.getView());
        geocodeSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(name,city);//查询名称，城市名称
        geocodeSearch.getFromLocationNameAsyn(query);

    }


    //poiSearch请求
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == AMapException.CODE_AMAP_SUCCESS){
            if ((poiResult!=null&&poiResult.getQuery()!=null)&&poiResult.getPois().size()!=0){
                poiSearchPresenter.setList(poiResult.getPois());
            }else {
                Toast.makeText(poiSearchPresenter.getView(),"查询结果为空，请检查输入的关键词",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(poiSearchPresenter.getView(),"查询失败,错误码"+i,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

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
