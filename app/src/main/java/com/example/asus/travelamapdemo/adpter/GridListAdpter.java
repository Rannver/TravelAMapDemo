package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.asus.travelamapdemo.R;


/**
 * Created by ASUS on 2017/7/21.
 */

public class GridListAdpter extends BaseAdapter {

    private Context context;

    public GridListAdpter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridlist,null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.image = (ImageView) view.findViewById(R.id.iv_notelist);
        return view;
    }

    static class ViewHolder{
        ImageView image;
    }
}
