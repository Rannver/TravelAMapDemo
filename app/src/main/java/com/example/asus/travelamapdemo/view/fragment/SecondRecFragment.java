package com.example.asus.travelamapdemo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.RecListAdpter;
import com.example.asus.travelamapdemo.contract.RecContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/22.
 */

public class SecondRecFragment extends BaseFragment implements RecContract.RecView {

    @BindView(R.id.reclist)
    RecyclerView reclist;
    Unbinder unbinder;
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
        presenter.start();
        return view;
    }

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
        reclist.setAdapter(new RecListAdpter(context));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
