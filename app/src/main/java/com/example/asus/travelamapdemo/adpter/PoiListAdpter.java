package com.example.asus.travelamapdemo.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.example.asus.travelamapdemo.R;

import java.util.List;

/**
 * Created by ASUS on 2017/7/14.
 */

public class PoiListAdpter extends RecyclerView.Adapter<PoiListAdpter.ViewHolder>{

    private List<PoiItem> list;

    public PoiListAdpter(List<PoiItem> list){
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poisearchlist,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textName.setText(list.get(position).getTitle());
        holder.textAddress.setText(""+list.get(position).getCityName()+list.get(position).getAdName()+list.get(position).getSnippet());
        if(onItemClickLister!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int poisition = holder.getLayoutPosition();
                    onItemClickLister.OnItemClickListener(holder.itemView,poisition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textName;
        private TextView textAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            textName= (TextView) itemView.findViewById(R.id.name_text);
            textAddress = (TextView) itemView.findViewById(R.id.address_text);
        }
    }

    //点击事件
    public interface OnItemClickLister{
        void OnItemClickListener(View view,int poisition);
    }
    private OnItemClickLister onItemClickLister;
    public void setOnItemClickLister(OnItemClickLister onItemClickLister){
        this.onItemClickLister = onItemClickLister;
    }
}
