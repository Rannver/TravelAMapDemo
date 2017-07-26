package com.example.asus.travelamapdemo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.RecListAdpter;
import com.example.asus.travelamapdemo.contract.RecContract;
import com.example.asus.travelamapdemo.presenter.RecPresenter;
import com.example.asus.travelamapdemo.util.ImageSlideUtil;
import com.example.asus.travelamapdemo.view.activity.WriteNoteActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/22.
 */

public class SecondRecFragment extends BaseFragment implements RecContract.RecView {

    @BindView(R.id.reclist)
    RecyclerView reclist;
    Unbinder unbinder;
    @BindView(R.id.btu_write)
    FloatingActionButton btuWrite;
    private View view;
    private Context context;
    private RecContract.RecPresenter presenter;

    private static String TAG = "SecondRecFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rec2, container, false);
        Log.d(TAG, "onCreateView: SecondRecFragment");
        unbinder = ButterKnife.bind(this, view);
        presenter = new RecPresenter(this);
        presenter.start();
        return view;
    }


    public SecondRecFragment(){}

    public SecondRecFragment(Context context) {
        this.context = context;
    }

    @Override
    public void setPresenter(RecContract.RecPresenter presenter) {
        this.presenter = checkNotNull(presenter);
    }


    @Override
    public void initView() {
        Log.d(TAG, "initView");
        LinearLayoutManager manager = new LinearLayoutManager(context);
        reclist.setLayoutManager(manager);
        RecListAdpter adpter = new RecListAdpter(context, presenter);
        reclist.setAdapter(adpter);
    }

    @Override
    public void setListScrollListener(final ImageSlideUtil util) {
        reclist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取第一个完全显示的ItemPosition
                    int firstVisibleItem = manager.findFirstCompletelyVisibleItemPosition();
                    if (firstVisibleItem > 0) {
                        util.setStopPlay();
                    } else {
                        util.setStartPlay();
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "onScrolled: dx = " + dx + ", dy = " + dy);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btu_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btu_write:
                Intent intent = new Intent(context, WriteNoteActivity.class);
                intent.putExtra("status",WriteNoteActivity.INTENT_BY_WRITE);
                startActivity(intent);
                break;
        }
    }
}
