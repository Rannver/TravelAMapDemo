package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.travelamapdemo.R;

/**
 * Created by ASUS on 2017/7/22.
 */

public class RecListAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;

    private static final int VIEWTYPE_VIEWPAGER = 0;
    private static final int VIEWTYPE_GRID = 1;
    private static final int VIEWTYPE_NOTE = 2;
    private static String TAG = "RecListAdpter";

    public RecListAdpter(Context context){
        Log.d(TAG, "RecListAdpter");
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view;
        switch (viewType){
            case VIEWTYPE_VIEWPAGER:
                view = LayoutInflater.from(context).inflate(R.layout.item_recpager,null);
                PagerViewHolder pagerViewHolder = new PagerViewHolder(view);
                return pagerViewHolder;
            case VIEWTYPE_GRID:
                view = LayoutInflater.from(context).inflate(R.layout.item_recgrid,null);
                GridViewHolder gridViewHolder = new GridViewHolder(view);
                return gridViewHolder;
            case VIEWTYPE_NOTE:
                view = LayoutInflater.from(context).inflate(R.layout.item_recnote,null);
                NoteViewHolder noteViewHolder = new NoteViewHolder(view);
                return noteViewHolder;
            default:
                Log.d(TAG, "onCreateViewHolder: default == NULL");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println(TAG+":"+holder);

        if (holder != null) {
            switch (position) {
                case 0:
                    setViewPager(holder);
                    break;
                case 1:
                    setGrid(holder);
                    break;
                default:
                    setNoteList(holder);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return VIEWTYPE_VIEWPAGER;
            case 1:
                return VIEWTYPE_GRID;
            default:
                return VIEWTYPE_NOTE;
        }
    }


    //设置游记item
    private void setNoteList(RecyclerView.ViewHolder holder) {

    }

    //设置景区Grid
    private void setGrid(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "setGrid");
    }

    //设置ViewPager
    private void setViewPager(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "setViewPager");
        RecPagerAdpter adpter = new RecPagerAdpter(context);
        PagerViewHolder pagerViewHolder = (PagerViewHolder) holder;
        pagerViewHolder.viewPager.setAdapter(adpter);
    }



    //ViewPager的Holder
    public class PagerViewHolder extends RecyclerView.ViewHolder{

        private ViewPager viewPager;

        public PagerViewHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewpager_rec);
        }
    }

    //Grid的Holder
    public class GridViewHolder extends RecyclerView.ViewHolder{

        public GridViewHolder(View itemView) {
            super(itemView);
        }
    }

    //note的Holder
    public class NoteViewHolder extends RecyclerView.ViewHolder{

        public NoteViewHolder(View itemView) {
            super(itemView);
        }
    }


}
