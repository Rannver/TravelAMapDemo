package com.example.asus.travelamapdemo.adpter;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.MapContract;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by ASUS on 2017/7/17.
 */

public class MapDialogAdpter {

    private MapContract.MapPresenter presenter;

    private static String TAG = "MapDialogAdpter";

    public MapDialogAdpter(){
    }

    public ViewHolder getViewHolder(View view,String name,String des) {

        TextView textName;
        TextView textDes;
        textName = (TextView) view.findViewById(R.id.text_dlg_name);
        textDes = (TextView) view.findViewById(R.id.text_dlg_description);

        textName.setText(name);
        textDes.setText(des);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    public void setFooter(View footer){
        Button btuCancel;
        Button btuRoad;
        btuCancel  = (Button) footer.findViewById(R.id.btu_cancel);
        btuRoad = (Button) footer.findViewById(R.id.btu_road);
        btuCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btuCancel");
            }
        });
        btuRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btuRoad");
            }
        });
    }


}
