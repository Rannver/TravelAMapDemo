package com.example.asus.travelamapdemo.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.amap.api.services.core.PoiItem;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.PoiListAdpter;
import com.example.asus.travelamapdemo.contract.PoiSearchContract;
import com.example.asus.travelamapdemo.presenter.PoiSearchPresenter;

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

    private static String TAG = "PoiSearchActivity";



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
                    presenter.poiSearch(newText,PoiSearchPresenter.FLAG_ENDPOINT_BY_INPUT);
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
                presenter.GeocodeSearch(list.get(poisition).getTitle(),list.get(poisition).getCityName());
            }
        });
        poilist.setAdapter(adpter);
        poilist.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public Context getContext() {
        return PoiSearchActivity.this;
    }

}
