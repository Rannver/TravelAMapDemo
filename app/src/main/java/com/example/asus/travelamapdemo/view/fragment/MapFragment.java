package com.example.asus.travelamapdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.MapView;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.MapContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ASUS on 2017/7/11.
 */

public class MapFragment extends Fragment implements MapContract.MapView {

    Unbinder unbinder;
    @BindView(R.id.mapview)
    MapView mapview;
    Unbinder unbinder1;
    private String TAG = "MapFragment";
    private View view;
    private MapContract.MapPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        unbinder1 = ButterKnife.bind(this, view);

        mapview.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void setPresenter(MapContract.MapPresenter presenter) {

    }

    @Override
    public void initMap() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapview.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }
}
