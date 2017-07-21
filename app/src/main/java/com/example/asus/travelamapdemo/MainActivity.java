package com.example.asus.travelamapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.asus.travelamapdemo.presenter.MapPresenter;
import com.example.asus.travelamapdemo.util.LocationInfoSingleton;
import com.example.asus.travelamapdemo.view.fragment.MapFragment;
import com.example.asus.travelamapdemo.view.fragment.RecommendFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private FragmentManager fragmentManger;
    private MapFragment mapFragment;
    private MapPresenter mapPresenter;
    private RecommendFragment recommendFragment;
    private  String  flag;

    public static String TAG = "MainActivity";
    public final static int INTENT_ACTIVITY_BY_POISEARCH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        flag = intent.getStringExtra("intent");
        initFragment();
    }

    private void initFragment() {
        fragmentManger = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManger.beginTransaction();
        hideFragment(transaction);
        switch (flag){
            case "Map":
                if (mapFragment==null){
                    mapFragment = new MapFragment(this);
                    transaction.add(R.id.framelayout, mapFragment);
                    mapPresenter = new MapPresenter(mapFragment);
                    Log.d("MainActivity", "creat mapFragment");
                }else{
                    transaction.show(mapFragment);
                }
                break;
            case "Note":
                if (recommendFragment==null){
                    recommendFragment = new RecommendFragment(this,fragmentManger);
                    transaction.add(R.id.framelayout,recommendFragment);
                }else{
                    transaction.show(recommendFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mapFragment!=null){
            transaction.hide(mapFragment);
        }
        if (recommendFragment!=null){
            transaction.hide(recommendFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult:"+requestCode+","+resultCode);
        switch (requestCode) {
            case INTENT_ACTIVITY_BY_POISEARCH:
                LocationInfoSingleton singleton = LocationInfoSingleton.getInfoSingleton();
                if (singleton.getPoint()!=null){
                    mapPresenter.setSearchMarkerPoint(singleton.getPoint(),singleton.getName(),singleton.getDes());
                }
                break;
        }
    }
}
