package com.example.asus.travelamapdemo.view.fragment;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.MapContract;
import com.example.asus.travelamapdemo.util.SensorEventHelper;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/11.
 */

public class MapFragment extends Fragment implements MapContract.MapView,LocationSource,AMapLocationListener {

    Unbinder unbinder;
    @BindView(R.id.mapview)
    MapView mapview;

    private String TAG = "MapFragment";
    private View view;
    private MapContract.MapPresenter presenter;
    private AMap aMap;
    private SensorEventHelper sensorEventHelper;
    private OnLocationChangedListener listener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationClientOption;
    private boolean firstFix = false;
    private Marker Locmarker;
    private Circle circle;


    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        unbinder = ButterKnife.bind(this, view);

        mapview.onCreate(savedInstanceState);
        aMap = mapview.getMap();

        presenter.start();


        return view;
    }

    @Override
    public void setPresenter(MapContract.MapPresenter presenter) {
        this.presenter = checkNotNull(presenter);
    }


    //设置amap的一些属性
    @Override
    public void initMap() {

        if(aMap==null){
            aMap = mapview.getMap();
        }
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);

        sensorEventHelper = new SensorEventHelper(getContext());
        if (sensorEventHelper!=null){
            sensorEventHelper.registerSensorListener();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapview.onDestroy();
        unbinder.unbind();

        if(locationClient!=null){
            locationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
        if (sensorEventHelper!=null){
            sensorEventHelper.registerSensorListener();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        if (sensorEventHelper!=null){
//            sensorEventHelper.unRegisterSensorListener();
//            sensorEventHelper.setCurrentMarker(null);
//            sensorEventHelper = null;
//        }
        mapview.onPause();
        deactivate();
        firstFix = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        System.out.println(TAG+":"+"listener = "+listener+",location = "+aMapLocation);
        if(listener!=null&&aMapLocation!=null){
            if(aMapLocation!=null&&aMapLocation.getErrorCode()==0){
                LatLng location = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                if(!firstFix){
                    firstFix = true;
                    //addCircle(location,aMapLocation.getAccuracy());//添加定位精度圆
                    addMarker(location);//添加定位图标
                    sensorEventHelper.setCurrentMarker(Locmarker);   //定位图标旋转
                }else {
                    circle.setCenter(location);
                    circle.setRadius(aMapLocation.getAccuracy());
                    Locmarker.setPosition(location);
                }
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,18));
            }else {
                System.out.println(TAG+":"+"errorcode = "+aMapLocation.getErrorCode()+","+aMapLocation.getLocationDetail());
            }
        }
    }

    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        listener = onLocationChangedListener;
        if(locationClient==null){
            locationClient = new AMapLocationClient(getContext());
            locationClientOption = new AMapLocationClientOption();
            //设置定位监听
            locationClient.setLocationListener(this);
            //设置为高精度模式
            locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            locationClient.setLocationOption(locationClientOption);
            locationClient.startLocation();
        }
    }

    //停止定位
    @Override
    public void deactivate() {
        listener = null;
        if(locationClient!=null){
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;
    }

    private void addCircle(LatLng latlng, double radius) {
        CircleOptions options = new CircleOptions();
        options.strokeWidth(1f);
        options.fillColor(FILL_COLOR);
        options.strokeColor(STROKE_COLOR);
        options.center(latlng);
        options.radius(radius);
        circle = aMap.addCircle(options);
    }

    private void addMarker(LatLng latlng) {
        if (Locmarker != null) {
            return;
        }
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.navi_map_gps_locked);
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);


        MarkerOptions options = new MarkerOptions();
        options.icon(des);
        options.anchor(0.5f, 0.5f);
        options.position(latlng);
        Locmarker = aMap.addMarker(options);
    }
}
