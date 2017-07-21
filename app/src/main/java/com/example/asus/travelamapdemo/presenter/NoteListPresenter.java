package com.example.asus.travelamapdemo.presenter;

import com.example.asus.travelamapdemo.contract.NoteListContract;
import com.example.asus.travelamapdemo.model.NoteListModel;

/**
 * Created by ASUS on 2017/7/21.
 */

public class NoteListPresenter implements NoteListContract.NoteListPresenter {

    private NoteListModel noteListModel;
    private NoteListContract.NoteListView noteListView;

    public NoteListPresenter(NoteListContract.NoteListView noteListView){
        this.noteListView = noteListView;
        noteListModel = new NoteListModel(NoteListPresenter.this);
        this.noteListView.setPresenter(NoteListPresenter.this);
    }

    @Override
    public void start() {
        noteListView.initView();
        noteListView.initNoteList();
    }
}
