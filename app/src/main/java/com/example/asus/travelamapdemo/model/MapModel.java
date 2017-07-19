package com.example.asus.travelamapdemo.model;


import android.app.ProgressDialog;
import android.util.Log;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.asus.travelamapdemo.contract.MapContract;

/**
 * Created by ASUS on 2017/7/10.
 */

public class MapModel implements RouteSearch.OnRouteSearchListener{

    private MapContract.MapPresenter mapPresenter;
    private ProgressDialog progressDialog;
    private RouteSearch routeSearch;
    private boolean isEnd = false;

    private static String TAG = "MapModel";

    public MapModel(MapContract.MapPresenter mapPresenter){
        this.mapPresenter = mapPresenter;
    }

    public void DriveRouteQuery(LatLonPoint start,LatLonPoint end,boolean isEnd){
        showProgress();
        this.isEnd = isEnd;
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(start,end);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo,RouteSearch.DRIVING_MULTI_CHOICE_AVOID_CONGESTION,null,null,"");
        routeSearch = new RouteSearch(mapPresenter.getContext());
        routeSearch.setRouteSearchListener(this);
        routeSearch.calculateDriveRouteAsyn(query);//异步规划驾车路线
    }

    public void showProgress(){
        if(progressDialog==null){
            progressDialog = new ProgressDialog(mapPresenter.getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("正在为您规划路线");
            progressDialog.show();
        }
    }

    public void showProgressDimiss(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    /**
     * 公交驾车路线
     */
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    /**
     *自驾路线
     */
    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        showProgressDimiss();
        if (i == AMapException.CODE_AMAP_SUCCESS){
            if(driveRouteResult!=null&&driveRouteResult.getPaths()!=null){
                if (driveRouteResult.getPaths().size()>0){
                    mapPresenter.setPathList(driveRouteResult,isEnd);
                }else {
                    Log.d(TAG, "onDriveRouteSearched: 未搜寻到合适的驾车路线");
                }

            }
        }
    }

    /**
     * 步行路线
     */
    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
