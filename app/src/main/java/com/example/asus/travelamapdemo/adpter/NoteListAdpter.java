package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.NoteListContract;

/**
 * Created by ASUS on 2017/7/21.
 */

public class NoteListAdpter extends RecyclerView.Adapter<NoteListAdpter.ViewHolder> {

    private NoteListContract.NoteListPresenter presenter;
    private Context context;

    public NoteListAdpter(NoteListContract.NoteListPresenter presenter,Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notelist,null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.gridlist.setNumColumns(3);
        viewHolder.gridlist.setAdapter(new GridListAdpter(parent.getContext()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_info;
        private GridView gridlist;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_noteTitle);
            tv_content = (TextView) itemView.findViewById(R.id.tv_noteContent);
            tv_info = (TextView) itemView.findViewById(R.id.tv_noteInfo);
            gridlist = (GridView) itemView.findViewById(R.id.grid_notelist);
        }
    }
}
