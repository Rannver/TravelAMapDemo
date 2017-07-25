package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.RecContract;
import com.example.asus.travelamapdemo.presenter.RecPresenter;
import com.example.asus.travelamapdemo.util.ImageSlideUtil;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by ASUS on 2017/7/22.
 */

public class RecListAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private int Img_Width;
    private int Img_Height;
    private ImageSlideUtil util;
    private RecContract.RecPresenter presenter;
    private ViewGroup parent;
    private int[] imgs = new int[]{R.drawable.iv_pager1,R.drawable.iv_pager2,R.drawable.iv_pager3};
    private String[] titles = new String[]{"大理双廊 | 无关风月，只恋洱海","张家界 | 谁人识得天子面,归来不看天下山","故宫 | 皇家气派余惊叹"};

    private static final int VIEWTYPE_VIEWPAGER = 0;
    private static final int VIEWTYPE_GRID = 1;
    private static final int VIEWTYPE_NOTE = 2;
    private static final int VIEWTYPE_TITLE_SIGHT = 3;
    private static final int VIEWTYPE_TITLE_NOTE = 4;
    private static String TAG = "RecListAdpter";

    public RecListAdpter(Context context,RecContract.RecPresenter presenter){
        Log.d(TAG, "RecListAdpter");
        this.context = context;
        this.presenter = presenter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        this.parent = parent;
        View view;
        switch (viewType){
            case VIEWTYPE_VIEWPAGER:
                view = LayoutInflater.from(context).inflate(R.layout.item_recpager,parent,false);
                PagerViewHolder pagerViewHolder = new PagerViewHolder(view);
                return pagerViewHolder;
            case VIEWTYPE_GRID:
                view = LayoutInflater.from(context).inflate(R.layout.item_recgrid,parent,false);
                GridViewHolder gridViewHolder = new GridViewHolder(view);
                return gridViewHolder;
            case VIEWTYPE_TITLE_SIGHT:
                view = LayoutInflater.from(context).inflate(R.layout.item_rectitile,parent,false);
                TitleViewHolder titleViewHolder1 = new TitleViewHolder(view);
                return titleViewHolder1;
            case VIEWTYPE_NOTE:
                view = LayoutInflater.from(context).inflate(R.layout.item_recnote,parent,false);
                NoteViewHolder noteViewHolder = new NoteViewHolder(view);
                noteViewHolder.linearLayout.measure(0,0);
//                System.out.println(TAG+":"+noteViewHolder.linearLayout.getMeasuredHeight()+","+noteViewHolder.linearLayout.getMeasuredWidth());
                Img_Width = noteViewHolder.linearLayout.getMeasuredWidth()/2;
                Img_Height = noteViewHolder.linearLayout.getMeasuredHeight()*9/10;
                return noteViewHolder;
            case VIEWTYPE_TITLE_NOTE:
                view = LayoutInflater.from(context).inflate(R.layout.item_rectitile,parent,false);
                TitleViewHolder titleViewHolder2 = new TitleViewHolder(view);
                return titleViewHolder2;
            default:
                Log.d(TAG, "onCreateViewHolder: default == NULL");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            switch (getItemViewType(position)) {
                case VIEWTYPE_VIEWPAGER:
                    setViewPager(holder);
                    break;
                case VIEWTYPE_TITLE_SIGHT:
                    setTiTle(holder,"HOT SIGHTS");
                    break;
                case VIEWTYPE_GRID:
                    setGrid(holder);
                    break;
                case VIEWTYPE_TITLE_NOTE:
                    setTiTle(holder,"HOT NOTES");
                    break;
                case VIEWTYPE_NOTE:
                    setNoteList(holder);
                    break;
                default:
                    Log.d(TAG, "onBindViewHolder: type is null");
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return VIEWTYPE_VIEWPAGER;
            case 1:
                return VIEWTYPE_TITLE_SIGHT;
            case 2:
                return VIEWTYPE_GRID;
            case 3:
                return VIEWTYPE_TITLE_NOTE;
            default:
                return VIEWTYPE_NOTE;
        }
    }

    //设置标题item
    private void setTiTle(RecyclerView.ViewHolder holder,String str) {
        TitleViewHolder tHolder = (TitleViewHolder) holder;
        tHolder.titile.setText(str);
    }


    //设置游记item
    private void setNoteList(RecyclerView.ViewHolder holder) {
        NoteViewHolder nHolder = (NoteViewHolder) holder;
        Picasso.with(context)
                .load(R.drawable.iv_pager1)
                .resize(Img_Width,Img_Height)
                .into(nHolder.image);
    }

    //设置景区Grid
    private void setGrid(RecyclerView.ViewHolder holder) {
        GridViewHolder gHolder = (GridViewHolder) holder;
        gHolder.sightsGrid.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        gHolder.sightsGrid.setAdapter(new SightGridAdpter(context));
    }

    //设置ViewPager
    private void setViewPager(RecyclerView.ViewHolder holder) {

        List<ImageView> views = new ArrayList<>();
        for(int i=0;i<imgs.length;i++){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imgs[i]);
            views.add(imageView);
        }

        RecPagerAdpter adpter = new RecPagerAdpter(context);
        PagerViewHolder pagerViewHolder = (PagerViewHolder) holder;
        pagerViewHolder.viewPager.setAdapter(adpter);
        if (util==null){
            util = new ImageSlideUtil(context,parent);
        }
        util.setTitle(pagerViewHolder.textView);
        util.setIndicator(pagerViewHolder.viewPager,pagerViewHolder.indicator,adpter.getCount());
        presenter.setListScrollListener(util);
    }


    //ViewPager的Holder
    public class PagerViewHolder extends RecyclerView.ViewHolder{
        
        private ViewPager viewPager;
        private LinearLayout indicator;
        private TextView  textView;

        public PagerViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "PagerViewHolder");
            viewPager = (ViewPager) itemView.findViewById(R.id.viewpager_rec);
            indicator = (LinearLayout) itemView.findViewById(R.id.viewpager_indicator);
            textView = (TextView) itemView.findViewById(R.id.viewpager_text);
        }
    }

    //Grid的Holder
    public class GridViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView sightsGrid;

        public GridViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "GridViewHolder");
            sightsGrid = (RecyclerView) itemView.findViewById(R.id.grid_sight);
        }
    }

    //note的Holder
    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private ImageView head;
        private TextView title;
        private TextView author;
        private TextView date;
        private TextView like;
        private LinearLayout linearLayout;

        public NoteViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "NoteViewHolder");
            image = (ImageView) itemView.findViewById(R.id.iv_noteImg);
            head = (ImageView) itemView.findViewById(R.id.iv_authorHead);
            title = (TextView) itemView.findViewById(R.id.tv_noteTitle);
            author = (TextView) itemView.findViewById(R.id.tv_noteauthor);
            date = (TextView) itemView.findViewById(R.id.tv_noteDate);
            like = (TextView) itemView.findViewById(R.id.tv_notelike);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lin_recNote);
        }
    }

    //title的Holder

    public class TitleViewHolder extends RecyclerView.ViewHolder{

        private TextView titile;

        public TitleViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "TitleViewHolder");
            titile = (TextView) itemView.findViewById(R.id.tv_titile);
        }
    }


}
