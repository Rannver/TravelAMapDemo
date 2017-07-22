package com.example.asus.travelamapdemo.contract;

import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/22.
 */

public interface RecContract {

    interface RecView extends BaseView<RecPresenter>{

        void initView();

    }

    interface RecPresenter extends BasePresenter{

    }

}
