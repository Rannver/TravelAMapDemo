package com.example.asus.travelamapdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;



import com.example.asus.travelamapdemo.presenter.MapPresenter;
import com.example.asus.travelamapdemo.view.fragment.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private FragmentManager fragmentManger;
    private MapFragment mapFragment;

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
        transaction.add(R.id.framelayout,mapFragment);
        new MapPresenter(mapFragment);
        Log.d("MainActivity","creat mapFragment");
        transaction.commit();
    }
}
