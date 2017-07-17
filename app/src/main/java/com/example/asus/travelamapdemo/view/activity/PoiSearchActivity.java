package com.example.asus.travelamapdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.asus.travelamapdemo.MainActivity;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.PoiListAdpter;
import com.example.asus.travelamapdemo.contract.PoiSearchContract;
import com.example.asus.travelamapdemo.presenter.PoiSearchPresenter;
import com.example.asus.travelamapdemo.util.LocationInfoSingleton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/13.
 */

public class PoiSearchActivity extends AppCompatActivity implements PoiSearchContract.PoiSearchView {

    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.poilist)
    RecyclerView poilist;

    private PoiSearchContract.PoiSearchPresenter presenter;
    private int flag;

    private static String TAG = "PoiSearchActivity";
    public final static int FLAG_INTENT_BY_MAP = 1;
    public final static int FLAG_INTENT_BY_TEAM = 2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisearch);
        ButterKnife.bind(this);

       presenter = new PoiSearchPresenter(PoiSearchActivity.this);
        presenter.start();
    }

    @Override
    public void setPresenter(PoiSearchContract.PoiSearchPresenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void initView() {
        searchview.setIconifiedByDefault(false);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",-1);
        poiSearch();
    }

    @Override
    public void poiSearch() {
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(TAG+":"+newText);
                if(newText!=null){
                    presenter.poiSearch(newText,flag);
                }
                return false;
            }
        });
    }

    @Override
    public void initPoiList(final List<PoiItem> list) {
        LinearLayoutManager manager = new LinearLayoutManager(PoiSearchActivity.this);
        poilist.setLayoutManager(manager);
        PoiListAdpter adpter = new PoiListAdpter(list);
        adpter.setOnItemClickLister(new PoiListAdpter.OnItemClickLister() {
            @Override
            public void OnItemClickListener(View view, int poisition) {
                presenter.GeocodeSearch(list.get(poisition));
            }
        });
        poilist.setAdapter(adpter);
        poilist.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void AmapIntent(LatLonPoint point,PoiItem poiItem) {

        LocationInfoSingleton singleton = LocationInfoSingleton.getInfoSingleton();
        switch (flag){
            case FLAG_INTENT_BY_MAP:
                //存入单例
                singleton.setPoint(point);
                singleton.setName(poiItem.getTitle());
                singleton.setDes(""+poiItem.getCityName()+poiItem.getAdName()+poiItem.getSnippet());
                setResult(MainActivity.INTENT_ACTIVITY_BY_POISEARCH);
                finish();
                break;
            case FLAG_INTENT_BY_TEAM:
                //存入单例
                singleton.setEndPoint(point);
                singleton.setEndName(poiItem.getTitle());
                singleton.setEndDes(""+poiItem.getCityName()+poiItem.getAdName()+poiItem.getSnippet());
                //这里跳回团队的逻辑...emmm....你自己写_(:зゝ∠)_

        }

    }

    @Override
    public Context getContext() {
        return PoiSearchActivity.this;
    }

}
