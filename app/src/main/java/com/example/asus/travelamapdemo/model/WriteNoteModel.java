package com.example.asus.travelamapdemo.model;

import com.example.asus.travelamapdemo.contract.WriteNoteContact;

/**
 * Created by ASUS on 2017/7/26.
 */

public class WriteNoteModel {

    private WriteNoteContact.WriteNotePresenter presenter;

    public WriteNoteModel(WriteNoteContact.WriteNotePresenter presenter){
       this.presenter = presenter;
    }
}
