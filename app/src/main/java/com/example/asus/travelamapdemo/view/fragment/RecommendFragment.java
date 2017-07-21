package com.example.asus.travelamapdemo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.NoteViewPagerAdpter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/20.
 */

public class RecommendFragment extends BaseFragment{

    @BindView(R.id.note_tabs)
    PagerSlidingTabStrip noteTabs;
    @BindView(R.id.note_viewpager)
    ViewPager noteViewpager;
    Unbinder unbinder;


    private View view;
    private ViewGroup viewGroup;
    private Context context;
    private FragmentManager fragmentManager;

    private static String TAG = "RecommendFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        this.viewGroup = container;
        unbinder = ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView: this is RecommendFragment");

        initView();

        return view;
    }

    public RecommendFragment(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }


    public void initView() {
        noteViewpager.setAdapter(new NoteViewPagerAdpter(fragmentManager,context));
        noteTabs.setViewPager(noteViewpager);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
