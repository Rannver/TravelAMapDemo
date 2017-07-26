package com.example.asus.travelamapdemo.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.WriteNoteContact;
import com.example.asus.travelamapdemo.presenter.WriteNotePresenter;
import com.example.asus.travelamapdemo.util.MatisseUtil;
import com.goyourfly.multi_picture.MultiPictureView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.ImageEngine;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by ASUS on 2017/7/26.
 */

public class WriteNoteActivity extends AppCompatActivity implements WriteNoteContact.WriteNoteView{


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.ed_writenote)
    EditText edWritenote;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.btu_loation)
    Button btuLoation;
    @BindView(R.id.multiple_image)
    MultiPictureView multipleImage;

    private WriteNoteContact.WriteNotePresenter presenter;

    private static String TAG = "WriteNoteActivity";
    public static final String INTENT_BY_WRITE = "write";
    public static final String INTENT_BY_CHANGE = "change";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writenote);
        ButterKnife.bind(this);
        presenter = new WriteNotePresenter(this);
        presenter.start();
    }

    @Override
    public void setPresenter(WriteNoteContact.WriteNotePresenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void initView() {
        multipleimageClick();
    }

    @OnClick({R.id.iv_location, R.id.btu_loation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_location:
                break;
            case R.id.btu_loation:
                break;
        }
    }

    private void multipleimageClick() {
        Log.d(TAG, "multipleimageClick");
        multipleImage.setAddClickCallback(new MultiPictureView.AddClickCallback() {
            @Override
            public void onAddClick(@NotNull View view) {
                MatisseUtil util = new MatisseUtil(WriteNoteActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==MatisseUtil.REQUEST_ADD_IMAGE&&resultCode == RESULT_OK){
            Log.d(TAG, "onActivityResult");
        }
    }
}
