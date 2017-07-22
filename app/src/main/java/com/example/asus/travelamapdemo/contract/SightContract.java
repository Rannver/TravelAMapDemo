package com.example.asus.travelamapdemo.contract;

import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/22.
 */

public interface SightContract {

    interface SightView extends BaseView<SightPresenter>{

        void initView();

        void initList();//加载瀑布流

    }

    interface SightPresenter extends BasePresenter{

    }
}
