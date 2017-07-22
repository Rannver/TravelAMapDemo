package com.example.asus.travelamapdemo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.SightListAdpter;
import com.example.asus.travelamapdemo.contract.SightContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.sephiroth.android.library.picasso.Picasso;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/20.
 */

public class SightFragment extends BaseFragment implements SightContract.SightView {

    @BindView(R.id.sightlist)
    RecyclerView sightlist;
    Unbinder unbinder;

    private View view;
    private Context context;
    private SightContract.SightPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sight, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.start();
        return view;
    }

    public SightFragment(Context context){
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(SightContract.SightPresenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void initView() {
        sightlist.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        SightListAdpter adpter = new SightListAdpter(context);
        adpter.getRandomHeights(20);
        sightlist.setAdapter(adpter);
        sightlist.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void initList() {

    }
}
