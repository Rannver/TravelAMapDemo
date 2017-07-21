package com.example.asus.travelamapdemo.model;

import com.example.asus.travelamapdemo.contract.NoteListContract;
import com.example.asus.travelamapdemo.presenter.NoteListPresenter;

/**
 * Created by ASUS on 2017/7/21.
 */

public class NoteListModel {

    private NoteListContract.NoteListPresenter noteListPresenter;

    public NoteListModel(NoteListContract.NoteListPresenter noteListPresenter){
        this.noteListPresenter = noteListPresenter;
    }
}
