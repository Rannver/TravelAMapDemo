package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.MapContract;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by ASUS on 2017/7/17.
 */

public class PathListAdpter extends BaseAdapter{

    private List<DrivePath> list;
    private MapContract.MapPresenter presenter;
    private Context context;
    private LayoutInflater layoutInflater;

    private static String TAG = "PathListAdpter";

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
        Log.d(TAG, "getView");
        View view = layoutInflater.inflate(R.layout.item_dialog_pathlist,parent,false);
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.textRoute = (TextView) view.findViewById(R.id.text_dlg_listRoute);
        viewHolder.textDistance = (TextView) view.findViewById(R.id.text_dlg_listDistance);
        viewHolder.textInfo = (TextView) view.findViewById(R.id.text_dlg_listInfo);
        viewHolder.textStep = (TextView) view.findViewById(R.id.text_dlg_liststep);
        view.setTag(viewHolder);

        viewHolder.textRoute.setText(list.get(position).getStrategy());
        viewHolder.textInfo.setText(getInfo(list.get(position)));
        if (list.get(position).getTollDistance()!=0){
            viewHolder.textDistance.setText(getDistance(list.get(position).getTollDistance()));
        }else {
            viewHolder.textDistance.setVisibility(View.GONE);
        }
        if (list.get(position).getSteps().size()>0){
            viewHolder.textStep.setText(getStep(list.get(position).getSteps()));
        }else {
            viewHolder.textStep.setVisibility(View.GONE);
        }


        return view;
    }

    private String getStep(List<DriveStep> steps) {
        String str="";
        for(int i = 0;i<5;i++){
            if(i>1){
                str+="→";
            }
            str += steps.get(i).getRoad();
        }
        if (steps.size()>4) {

            str+="→…";
        }
        return str;
    }

    private String getDistance(float distance){
        String str;
        DecimalFormat df = new DecimalFormat("#.#");
        if (distance>900){
            distance /= 1000;
            df.format(distance);
            str = distance+"km";
        }else {
            df.format(distance);
            str = distance+"m";
        }
        return str;
    }

    private String getInfo(DrivePath drivePath){
        String str = "";
        if(drivePath.getTotalTrafficlights()!=0){
            str +="红绿灯"+drivePath.getTotalTrafficlights()+"个";
            if(drivePath.getTolls()!=0) {
                str += "  ";
            }
        }
        if(drivePath.getTolls()!=0){
            str += "过路费"+drivePath.getTolls()+"元";
        }
        return str;
    }

    static class ViewHolder{
        TextView textRoute;
        TextView textDistance;
        TextView textInfo;
        TextView textStep;
    }
}
