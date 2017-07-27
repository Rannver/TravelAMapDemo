package com.example.asus.travelamapdemo.util;

import android.content.Context;
import android.widget.Toast;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.contract.PoiSearchContract;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/7/23.
 */

public class PoiSearchUtil implements PoiSearch.OnPoiSearchListener{

    public static final int POISEARCH_QUERY_MARKER = 1;
    public static final int POISEARCH_QUERY_ENDPOINT = 2;
    public static final int POISEARCH_QUERY_SIGHTS = 3;
    public static final int POISEARCH_QUERY_NOTELOCATION = 4;

    public static final String AMAP_POI_CODE_TRAVEL = "110200";


    private PoiResult poiResult;
    private Context context;
    private BasePresenter presenter;
    private int flag;

    public PoiSearchUtil(Context context,BasePresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void doSearchQuery(String str,String code,String city,int flag){
        this.flag = flag;
        PoiSearch.Query query = null;
        switch (flag){
            case POISEARCH_QUERY_MARKER:
                //从地图查询Marker
                query = new PoiSearch.Query(str,"","");//地址搜索，第三参数默认为空，全国范围内搜索
                break;
            case POISEARCH_QUERY_ENDPOINT:
                //目的地查询
                query = new PoiSearch.Query(str,AMAP_POI_CODE_TRAVEL,"");//景区搜索第三参数默认为空，全国范围内搜索
                break;
            case POISEARCH_QUERY_SIGHTS:
                //景区列表查询
                query = new PoiSearch.Query("",AMAP_POI_CODE_TRAVEL,city);
                break;
            case POISEARCH_QUERY_NOTELOCATION:
                //游记地点标记查询
                query = new PoiSearch.Query(str,AMAP_POI_CODE_TRAVEL,"");//景区搜索第三参数默认为空，全国范围内搜索
                break;
            default:
                break;
        }
        if (query!=null){
            PoiSearch poiSearch = new PoiSearch(context,query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.searchPOIAsyn();
        }

    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == AMapException.CODE_AMAP_SUCCESS){
            if ((poiResult!=null&&poiResult.getQuery()!=null)&&poiResult.getPois().size()!=0){
                setList(poiResult);
            }else {
                Toast.makeText(context,"查询结果为空，请检查输入的关键词",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context,"查询失败,错误码"+i,Toast.LENGTH_SHORT).show();
        }
    }

    private void setList(PoiResult poiResult) {
        //presenter逻辑在这里写
        switch (flag){
            case POISEARCH_QUERY_NOTELOCATION:
                //游记地点标记查询
            case POISEARCH_QUERY_MARKER:
                //从地图查询Marker
            case POISEARCH_QUERY_ENDPOINT:
                //目的地查询
                PoiSearchContract.PoiSearchPresenter searchPresenter = (PoiSearchContract.PoiSearchPresenter) presenter;
                searchPresenter.setList(poiResult.getPois());
                break;
            case POISEARCH_QUERY_SIGHTS:
                //景区列表查询

                break;
            default:
                break;
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
