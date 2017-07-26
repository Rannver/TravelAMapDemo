package com.example.asus.travelamapdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.view.activity.WriteNoteActivity;
import com.google.common.io.Resources;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.ImageEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by ASUS on 2017/7/26.
 */

public class MatisseUtil {

    private Activity activity;

    public static final int REQUEST_ADD_IMAGE = 2;
    public static final int  MAX_IMAGE_CHOOSE = 9;
    private static String TAG = "MatisseUtil";


    public MatisseUtil(Activity activity){
        this.activity = activity;
        start();
    }

    private void start() {
        Matisse.from(activity)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
                .gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new ImageEngine() {
                    @Override
                    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
                        Picasso.with(context)
                                .load(uri)
                                .fit()
                                .into(imageView);
                    }

                    @Override
                    public void loadAnimatedGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
                        Log.d(TAG, "loadAnimatedGifThumbnail");
                    }

                    @Override
                    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
                        Log.d(TAG, "loadImage");
                        Picasso.with(context)
                                .load(uri)
                                .into(imageView);
                    }

                    @Override
                    public void loadAnimatedGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
                        Log.d(TAG, "loadAnimatedGifImage");
                    }

                    @Override
                    public boolean supportAnimatedGif() {
                        return false;
                    }
                })
                .forResult(REQUEST_ADD_IMAGE);
    }
}
