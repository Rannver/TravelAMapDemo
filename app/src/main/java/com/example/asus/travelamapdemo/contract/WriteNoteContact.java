package com.example.asus.travelamapdemo.contract;

import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/26.
 */

public interface WriteNoteContact {

    interface WriteNoteView extends BaseView<WriteNotePresenter>{

        public void initView();

    }

    interface WriteNotePresenter extends BasePresenter{

    }

}
