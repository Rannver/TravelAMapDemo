package com.example.asus.travelamapdemo.view.fragment;

import android.support.v4.app.Fragment;

import com.example.asus.travelamapdemo.view.listener.FragmentBackListener;


/**
 * Created by john on 2017/1/6.
 */

public class BaseFragment extends Fragment implements FragmentBackListener {
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
