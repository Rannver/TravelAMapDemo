package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asus.travelamapdemo.presenter.NoteListPresenter;
import com.example.asus.travelamapdemo.presenter.SightPresenter;
import com.example.asus.travelamapdemo.view.fragment.NoteListFragment;
import com.example.asus.travelamapdemo.view.fragment.SightFragment;

/**
 * Created by ASUS on 2017/7/20.
 */

public class NoteViewPagerAdpter extends FragmentPagerAdapter {

    String[] tabs= {"游记","景区"};

    private NoteListFragment noteListFragment;
    private SightFragment sightFragment;
    private Context context;



    public NoteViewPagerAdpter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                noteListFragment = new NoteListFragment(context);
                new NoteListPresenter(noteListFragment);
                return noteListFragment;
            case 1:
                sightFragment = new SightFragment(context);
                new SightPresenter(sightFragment);
                return sightFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
