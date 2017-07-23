package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;

/**
 * Created by ASUS on 2017/7/23.
 */

public class SightGridAdpter extends RecyclerView.Adapter<SightGridAdpter.ViewHolder> {

    private Context context;
    private View view;

    private String[] sights = new String[]{"木兰天池","黄鹤楼","武汉欢乐谷","东湖磨山","武汉大学","光谷广场","江滩公园","昙华林","宝通寺","湖北省博物馆"};

    public SightGridAdpter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.item_sightlist,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(sights[position]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView  = (TextView) itemView.findViewById(R.id.tv_sight);
        }
    }
}
