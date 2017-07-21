package com.example.asus.travelamapdemo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.adpter.NoteListAdpter;
import com.example.asus.travelamapdemo.contract.NoteListContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/20.
 */

public class NoteListFragment extends BaseFragment implements NoteListContract.NoteListView{

    @BindView(R.id.notelist)
    RecyclerView notelist;
    Unbinder unbinder;


    private View view;
    private Context context;
    private NoteListContract.NoteListPresenter noteListPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notelist, container, false);
        unbinder = ButterKnife.bind(this, view);
        noteListPresenter.start();
        return view;
    }

    public NoteListFragment(Context context){
        this.context = context;
    }

    @Override
    public void setPresenter(NoteListContract.NoteListPresenter presenter) {
        this.noteListPresenter = checkNotNull(presenter);
    }

    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        notelist.setLayoutManager(manager);
    }

    @Override
    public void initNoteList() {
        notelist.setAdapter(new NoteListAdpter(noteListPresenter,context));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
