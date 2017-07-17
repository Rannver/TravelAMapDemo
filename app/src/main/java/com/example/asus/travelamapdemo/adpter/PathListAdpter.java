package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.amap.api.services.route.DrivePath;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.MapContract;

import java.util.List;

/**
 * Created by ASUS on 2017/7/17.
 */

public class PathListAdpter extends BaseAdapter{

    private List<DrivePath> list;
    private MapContract.MapPresenter presenter;
    private Context context;
    private LayoutInflater layoutInflater;

    public PathListAdpter(Context context, List<DrivePath> list, MapContract.MapPresenter presenter){
        this.context = context;
        this.list = list;
        this.presenter = presenter;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_dialog_pathlist,parent,false);

        return view;
    }

    static class ViewHolder{
        TextView textRoute;
        TextView textDistance;
        TextView textInfo;
    }
}
