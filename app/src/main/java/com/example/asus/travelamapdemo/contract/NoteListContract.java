package com.example.asus.travelamapdemo.contract;

import com.example.asus.travelamapdemo.BasePresenter;
import com.example.asus.travelamapdemo.BaseView;

/**
 * Created by ASUS on 2017/7/21.
 */

public interface NoteListContract {

    interface NoteListView extends BaseView<NoteListPresenter>{

        void initView();

        void initNoteList();//加载游记列表

    }

    interface NoteListPresenter extends BasePresenter{

    }

}
