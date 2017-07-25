package com.example.asus.travelamapdemo.util;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asus.travelamapdemo.R;

/**
 * Created by ASUS on 2017/7/23.
 */

public class ImageSlideUtil {

    private Context context;
    private Handler  handler = new Handler();
    private boolean isAutoPlay ;
    private ViewPager viewPager;
    private LinearLayout indicator;
    private int currentItem;
    private int count;
    private int delay = 3000;
    private boolean isF=true;
    private SparseBooleanArray isLarge;

    private Animator animatorToLarge;
    private Animator animatorToSmall;

    public ImageSlideUtil(Context context){
        this.context = context;
        animatorToLarge =  AnimatorInflater.loadAnimator(context, R.animator.scale_to_large);
        animatorToSmall =  AnimatorInflater.loadAnimator(context, R.animator.scale_to_small);
    }


    /**
     * 设置指示器
     */
    public void setIndicator(ViewPager viewPager, LinearLayout indicator, int count) {

        int dotSize = 12;
        int dotSpace = 12;

        this.viewPager = viewPager;
        this.indicator = indicator;
        this.count = count;

        isLarge = new SparseBooleanArray();
        // 记得创建前先清空数据，否则会受遗留数据的影响。
        indicator.removeAllViews();
        for (int i = 0; i < count; i++) {
            View view = new View(context);
            view.setBackgroundResource(R.drawable.dot_unselected);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dotSize, dotSize);
            layoutParams.leftMargin = dotSpace / 2;
            layoutParams.rightMargin = dotSpace / 2;
            layoutParams.topMargin = dotSpace / 2;
            layoutParams.bottomMargin = dotSpace / 2;
            indicator.addView(view, layoutParams);
            isLarge.put(i, false);
        }
        indicator.getChildAt(0).setBackgroundResource(R.drawable.dot_selected);
//        animatorToLarge.setTarget(indicator.getChildAt(0));
//        animatorToLarge.start();
        isLarge.put(0, true);
        setListener(count,isLarge);
        starPlay();
    }

    //自动轮播

    private Runnable task1 = new Runnable() {
        @Override
        public void run() {
            Log.d("mmm", "run: "+currentItem +" "+task1.hashCode());
            if (isAutoPlay) {

                //这里只适用于少数，更多轮播的情况需要优化算法
                if ((currentItem % count + 1) != count) {
                    currentItem = currentItem % count + 1;
                } else {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem);
            }
            handler.postDelayed(task1,delay);
        }
    };



    private void starPlay() {
        isAutoPlay = true;
        if (isF){
            handler.postDelayed(task1, delay);
            isF=false;
        }
        Log.d("mmmmmmmmm", "starPlay: "+task1.hashCode());
    }

    public void setStartPlay(){
        isAutoPlay = true;

    }

    public void setStopPlay(){
        isAutoPlay = false;

    }

    /**
     *设置监听变化
     */
    private void setListener(final int count,final SparseBooleanArray isLarge) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<count;i++){
                    if (i==position){
                        indicator.getChildAt(i).setBackgroundResource(R.drawable.dot_selected);
//                        if (!isLarge.get(i)) {
//                            animatorToLarge.setTarget(indicator.getChildAt(i));
//                            animatorToLarge.start();
//                            isLarge.put(i, true);
//                        }
                    }else {
                        indicator.getChildAt(i).setBackgroundResource(R.drawable.dot_unselected);
//                        if (isLarge.get(i)) {
//                            animatorToSmall.setTarget(indicator.getChildAt(i));
//                            animatorToSmall.start();
//                            isLarge.put(i, false);
//                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
