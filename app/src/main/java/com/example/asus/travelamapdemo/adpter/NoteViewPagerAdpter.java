package com.example.asus.travelamapdemo.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asus.travelamapdemo.view.fragment.NoteListFragment;
import com.example.asus.travelamapdemo.view.fragment.SightFragment;

/**
 * Created by ASUS on 2017/7/20.
 */

public class NoteViewPagerAdpter extends FragmentPagerAdapter {

    String[] tabs= {"游记","景区"};

    private NoteListFragment noteListFragment;
    private SightFragment sightFragment;



    public NoteViewPagerAdpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                noteListFragment = new NoteListFragment();
                return noteListFragment;
            case 1:
                sightFragment = new SightFragment();
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
