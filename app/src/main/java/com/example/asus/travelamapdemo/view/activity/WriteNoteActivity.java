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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.travelamapdemo.R;
import com.example.asus.travelamapdemo.contract.WriteNoteContact;
import com.example.asus.travelamapdemo.presenter.WriteNotePresenter;
import com.example.asus.travelamapdemo.util.MatisseUtil;
import com.goyourfly.multi_picture.ImageLoader;
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
import it.sephiroth.android.library.picasso.Picasso;

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
    private String flag;

    private static String TAG = "WriteNoteActivity";
    public static final String INTENT_BY_WRITE = "write";
    public static final String INTENT_BY_CHANGE = "change";
    public static final int REQUEST_BY_POISEARCH = 1;

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

        Intent intent = getIntent();
        if (intent!=null){
            flag = intent.getStringExtra("status");
        }

        initMultiPictureView();
        multipleimageClick();
        initbar();
    }

    @OnClick({R.id.iv_location, R.id.btu_loation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_location:
            case R.id.btu_loation:
                Intent intent = new Intent(WriteNoteActivity.this,PoiSearchActivity.class);
                intent.putExtra("flag",PoiSearchActivity.FLAG_INTENT_BY_NOTE);
                startActivityForResult(intent,REQUEST_BY_POISEARCH);
                break;
        }
    }

    private void initbar() {
        setSupportActionBar(toolBar);
        toolbarTitle.setText("编辑游记");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initMultiPictureView() {
        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, Uri uri) {
                Picasso.with(WriteNoteActivity.this)
                        .load(uri)
                        .into(imageView);
            }
        });
    }

    private void multipleimageClick() {
        Log.d(TAG, "multipleimageClick");
        multipleImage.setAddClickCallback(new MultiPictureView.AddClickCallback() {
            @Override
            public void onAddClick(@NotNull View view) {
                MatisseUtil util = new MatisseUtil(WriteNoteActivity.this,multipleImage);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.send:
                //发送后台逻辑
                break;
            case R.id.change:
                //修改后台逻辑
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (flag){
            case INTENT_BY_WRITE:
                getMenuInflater().inflate(R.menu.toolbar_note,menu);
                break;
            case INTENT_BY_CHANGE:
                getMenuInflater().inflate(R.menu.toolbar_note2,menu);
                break;

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: "+resultCode);
        if (resultCode == RESULT_OK){
            Log.d(TAG, "onActivityResult"+requestCode);
            switch (requestCode){
                case MatisseUtil.REQUEST_ADD_IMAGE:
                    //添加图片显示
                    multipleImage.addItem(Matisse.obtainResult(data));
                    break;
                case REQUEST_BY_POISEARCH:
                    //回调显示地址名字
                    if (data.getStringExtra("sight")!=null){
                        btuLoation.setText(data.getStringExtra("sight"));
                    }
                    break;

            }
        }
    }
}
