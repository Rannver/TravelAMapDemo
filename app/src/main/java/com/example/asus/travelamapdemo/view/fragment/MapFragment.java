package com.example.asus.travelamapdemo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

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
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.example.asus.travelamapdemo.MainActivity;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.MapDialogAdpter;
import com.example.asus.travelamapdemo.adpter.PathListAdpter;
import com.example.asus.travelamapdemo.contract.MapContract;
import com.example.asus.travelamapdemo.util.DrivingRouteOverLay;
import com.example.asus.travelamapdemo.util.LocationInfoSingleton;
import com.example.asus.travelamapdemo.util.SensorEventHelper;
import com.example.asus.travelamapdemo.view.activity.PoiSearchActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/11.
 */

public class MapFragment extends BaseFragment implements MapContract.MapView, LocationSource, AMapLocationListener,View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.mapview)
    MapView mapview;
    @BindView(R.id.searchview_map)
    SearchView searchviewMap;
    @BindView(R.id.btu_group)
    FloatingActionButton btuGroup;
    @BindView(R.id.btu_say)
    ToggleButton btuSay;
    @BindView(R.id.btu_history)
    FloatingActionButton btuHistory;
    @BindView(R.id.btu_endpoint)
    FloatingActionButton btuEndpoint;

    private String TAG = "MapFragment";
    private View view;
    private MapContract.MapPresenter presenter;
    private AMap aMap;
    private SensorEventHelper sensorEventHelper;
    private OnLocationChangedListener listener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationClientOption;
    private boolean firstFix = false;
    private boolean pathDrawFlag = false;
    private Marker Locmarker;
    private Circle circle;
    private Marker marker;
    private Marker endMarker;
    private MapDialogAdpter dialogAdpter;
    private String markerName;
    private String endMarkerName;
    private DialogPlus routeDialogPlus;
    private DrivingRouteOverLay routeOverlay;
    private DrivingRouteOverLay endRouteOverlay;


    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);

    private Context context;
    private ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        unbinder = ButterKnife.bind(this, view);

        mapview.onCreate(savedInstanceState);
        aMap = mapview.getMap();

        viewGroup = container;
        presenter.start();


        return view;
    }

    public MapFragment(Context context) {
        this.context = context;
    }

    @Override
    public void setPresenter(MapContract.MapPresenter presenter) {
        this.presenter = checkNotNull(presenter);
    }


    @Override
    public void initView() {
        searchviewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PoiSearchActivity.class);
                intent.putExtra("flag", PoiSearchActivity.FLAG_INTENT_BY_MAP);
                getActivity().startActivityForResult(intent, MainActivity.INTENT_ACTIVITY_BY_POISEARCH);
            }
        });
    }

    //设置amap的一些属性
    @Override
    public void initMap() {

        if (aMap == null) {
            aMap = mapview.getMap();
        }
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);

        sensorEventHelper = new SensorEventHelper(getContext());
        if (sensorEventHelper != null) {
            sensorEventHelper.registerSensorListener();
        }

        dialogAdpter = new MapDialogAdpter(context, presenter);

    }

    @Override
    public void initEndPoint() {
        if (endMarker != null) {
            endMarker.destroy();
        }
        LocationInfoSingleton singleton = LocationInfoSingleton.getInfoSingleton();
        if (singleton.getEndPoint() != null) {
            LatLonPoint latLonPoint = singleton.getEndPoint();
            final LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
            Bitmap bMap = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.ic_endpoint);

            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bMap);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.draggable(false);
            markerOptions.icon(bitmapDescriptor);
            markerOptions.position(latLng);
            endMarker = aMap.addMarker(markerOptions);
            endMarkerName = singleton.getEndName();
            setMarkerOnclickListener(latLng, singleton.getEndName(), singleton.getEndDes());
        }
    }


    @Override
    public void initMarkerBySearch(final LatLonPoint point, final String name, final String des) {
        if (marker != null) {
            marker.destroy();
        }
        final LatLng latLng = new LatLng(point.getLatitude(), point.getLongitude());
        marker = aMap.addMarker(new MarkerOptions().position(latLng));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        markerName = name;
        setMarkerOnclickListener(latLng, name, des);
    }

    @Override
    public void setMarkerOnclickListener(final LatLng latLng, final String name, final String des) {
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //dialog puls
                if (marker.getPosition().equals(latLng)) {
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_map, viewGroup, false);
                    View footer = LayoutInflater.from(context).inflate(R.layout.dialog_footer, viewGroup, false);
                    dialogAdpter.setMarker(marker);
                    if (marker == endMarker) {
                        dialogAdpter.setEndFlag(true);
                    }

                    DialogPlus dialogPlus = DialogPlus.newDialog(context)
                            .setFooter(footer)
                            .setContentHolder(dialogAdpter.getViewHolder(view, name, des))
                            .setGravity(Gravity.BOTTOM)
                            .create();
                    dialogPlus.show();

                    dialogAdpter.setDialogPlus(dialogPlus);
                    dialogAdpter.setFooter(dialogPlus.getFooterView(), pathDrawFlag);
                }

                return false;
            }
        });
    }

    @Override
    public void showDrivePathList(final DriveRouteResult result, final boolean isEnd) {
        List<DrivePath> list = result.getPaths();
        System.out.println(TAG + ":" + list);
        PathListAdpter adpter = new PathListAdpter(context, list, presenter);
        ListHolder listHolder = new ListHolder();
        listHolder.getView(LayoutInflater.from(context), viewGroup);
        listHolder.setAdapter(adpter);
        final DialogPlus dialogPlus = DialogPlus.newDialog(context)
                .setContentHolder(listHolder)
                .setHeader(R.layout.dialog_head)
                .setAdapter(adpter)
                .setGravity(Gravity.BOTTOM)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        presenter.setPathLine(result.getStartPos(), result.getTargetPos(), result.getPaths().get(position), isEnd);
                    }
                })
                .create();
        dialogPlus.show();
        routeDialogPlus = dialogPlus;
        dialogAdpter.setHead(dialogPlus.getHeaderView(), markerName);
    }

    @Override
    public void showPathLine(LatLonPoint start, LatLonPoint end, DrivePath path, boolean isEnd) {
        if (routeDialogPlus != null) {
            routeDialogPlus.dismiss();
        }
        if (routeOverlay != null) {
            routeOverlay.removeFromMap();
        }
        routeOverlay = showLine(start, end, path);
    }

    private DrivingRouteOverLay showLine(LatLonPoint start, LatLonPoint end, DrivePath path) {
        DrivingRouteOverLay overLay;
        overLay = new DrivingRouteOverLay(context, aMap, path, start, end, null, presenter);
        overLay.setNodeIconVisibility(true);
        overLay.removeFromMap();
        overLay.addToMap();
        overLay.zoomToSpan();
        return overLay;
    }

    @Override
    public void CancelPathLine() {
        if (routeOverlay != null) {
            routeOverlay.removeFromMap();
        }
    }


    @Override
    public Context getMapContext() {
        return context;
    }

    @Override
    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapview.onDestroy();
        unbinder.unbind();

        if (locationClient != null) {
            locationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
        if (sensorEventHelper != null) {
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
        System.out.println(TAG + ":" + "listener = " + listener + ",location = " + aMapLocation);
        if (listener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                LatLng location = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                if (!firstFix) {
                    firstFix = true;
                    //addCircle(location,aMapLocation.getAccuracy());//添加定位精度圆
                    addMarker(location);//添加定位图标
                    sensorEventHelper.setCurrentMarker(Locmarker);   //定位图标旋转
                } else {
                    circle.setCenter(location);
                    circle.setRadius(aMapLocation.getAccuracy());
                    Locmarker.setPosition(location);
                }
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
            } else {
                System.out.println(TAG + ":" + "errorcode = " + aMapLocation.getErrorCode() + "," + aMapLocation.getLocationDetail());
            }
        }
    }


    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        listener = onLocationChangedListener;
        if (locationClient == null) {
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
        if (locationClient != null) {
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
        dialogAdpter.setStartMaker(Locmarker);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btu_endpoint:
                if (endMarker!=null){
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(endMarker.getPosition(), 18));
                }
                break;
            case R.id.btu_group:
                //点击团队成员逻辑在这里写
                break;
            case R.id.btu_history:
                //点击对讲历史记录逻辑在这里写
                break;
            case R.id.btu_say:
                //点击对讲逻辑在这里写
                //布局的按钮样式还没设置...记得...
                break;
            default:
                break;
        }
    }
}
