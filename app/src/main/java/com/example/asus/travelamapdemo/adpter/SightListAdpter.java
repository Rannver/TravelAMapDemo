package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/7/22.
 */

public class SightListAdpter extends RecyclerView.Adapter<SightListAdpter.ViewHolder> {

    private List<Integer> heights;
    private Context context;

    public SightListAdpter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sightlist,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
        layoutParams.height = heights.get(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public void getRandomHeights(int size){
        heights = new ArrayList<>();
        for(int i=0; i < size;i++){
            //随机的获取一个范围为200-600直接的高度
            heights.add((int)(100+Math.random()*200));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_sight);
        }
    }
}
