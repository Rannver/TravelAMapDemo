package com.example.asus.travelamapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.FrameLayout;

import com.amap.api.services.core.LatLonPoint;
import com.example.asus.travelamapdemo.presenter.MapPresenter;
import com.example.asus.travelamapdemo.util.LocationInfoSingleton;
import com.example.asus.travelamapdemo.view.activity.PoiSearchActivity;
import com.example.asus.travelamapdemo.view.fragment.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private FragmentManager fragmentManger;
    private MapFragment mapFragment;
    private MapPresenter mapPresenter;

    public static String TAG = "MainActivity";
    public final static int INTENT_ACTIVITY_BY_POISEARCH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
    }

    private void initFragment() {
        fragmentManger = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManger.beginTransaction();
        mapFragment = new MapFragment();
        transaction.add(R.id.framelayout, mapFragment);
        mapPresenter = new MapPresenter(mapFragment);
        Log.d("MainActivity", "creat mapFragment");
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult:"+requestCode+","+resultCode);
        switch (requestCode) {
            case INTENT_ACTIVITY_BY_POISEARCH:
                LocationInfoSingleton singleton = LocationInfoSingleton.getInfoSingleton();
                mapPresenter.setSearchMarkerPoint(singleton.getPoint());
//                System.out.println(TAG+":"+singleton.getPoint());
                break;
        }
    }
}
