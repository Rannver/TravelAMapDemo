package com.example.asus.travelamapdemo.presenter;

import com.example.asus.travelamapdemo.contract.WriteNoteContact;
import com.example.asus.travelamapdemo.model.WriteNoteModel;

/**
 * Created by ASUS on 2017/7/26.
 */

public class WriteNotePresenter implements WriteNoteContact.WriteNotePresenter {

    private WriteNoteModel model;
    private WriteNoteContact.WriteNoteView view;

    public WriteNotePresenter(WriteNoteContact.WriteNoteView view){
        this.view = view;
        model = new WriteNoteModel(WriteNotePresenter.this);
        this.view.setPresenter(WriteNotePresenter.this);
    }

    @Override
    public void start() {
        view.initView();
    }
}
