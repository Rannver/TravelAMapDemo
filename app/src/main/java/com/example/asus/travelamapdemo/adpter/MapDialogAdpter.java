package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.Marker;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.MapContract;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by ASUS on 2017/7/17.
 */

public class MapDialogAdpter {

    private Context context;
    private MapContract.MapPresenter presenter;
    private Marker marker;
    private boolean endFlag = false;
    private DialogPlus dialogPlus;

    private static String TAG = "MapDialogAdpter";

    public MapDialogAdpter(Context context,MapContract.MapPresenter presenter){
        this.context = context;
        this.presenter = presenter;
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
                if (!endFlag){
                    marker.destroy();
                    dialogPlus.dismiss();
                }else {
                    Toast.makeText(context,"这是目的地不能取消标记的噢~",Toast.LENGTH_SHORT).show();
                    dialogPlus.dismiss();
                }

            }
        });
        btuRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btuRoad");
            }
        });
    }

    public void setEndFlag(boolean endFlag){
        this.endFlag = endFlag;
    }

    public void setDialogPlus(DialogPlus dialogPlus) {
        this.dialogPlus = dialogPlus;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
