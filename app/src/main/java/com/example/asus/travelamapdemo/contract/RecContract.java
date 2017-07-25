package com.example.asus.travelamapdemo.contract;

import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;
import com.example.asus.travelamapdemo.util.ImageSlideUtil;

/**
 * Created by ASUS on 2017/7/22.
 */

public interface RecContract {

    interface RecView extends BaseView<RecPresenter>{

        void initView();

        void setListScrollListener(ImageSlideUtil util);

    }

    interface RecPresenter extends BasePresenter{

        void setListScrollListener(ImageSlideUtil util);

    }

}
