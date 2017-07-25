package com.example.asus.travelamapdemo.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by ASUS on 2017/7/22.
 */

public class RecPagerAdpter extends PagerAdapter {

    private Context context;
    private int[] imgs = new int[]{R.drawable.iv_pager1,R.drawable.iv_pager2,R.drawable.iv_pager3};
    private String[] titles = new String[]{"大理双廊 | 无关风月，只恋洱海","张家界 | 谁人识得天子面,归来不看天下山","故宫 | 皇家气派余惊叹"};

    private List<ImageView> views = new ArrayList<>();

    public RecPagerAdpter(Context context){
        this.context = context;
        initPager();
    }

    private void initPager(){
        for(int i=0;i<imgs.length;i++){
            ImageView img = new ImageView(context);
            img.setImageResource(imgs[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            views.add(img);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position%=views.size();
        if (position<0){
            position = views.size()+position;
        }
        ImageView imageView = views.get(position);
        ViewParent vp = imageView.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object==view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

}
