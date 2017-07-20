package com.example.asus.travelamapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS on 2017/7/20.
 */

public class ChooseActivity extends AppCompatActivity {
    @BindView(R.id.btu_map)
    Button btuMap;
    @BindView(R.id.btu_note)
    Button btuNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btu_map, R.id.btu_note})
    public void onViewClicked(View view) {
        Intent intent;
        intent = new Intent(ChooseActivity.this,MainActivity.class);
        switch (view.getId()) {
            case R.id.btu_map:
                intent.putExtra("intent","Map");
                startActivity(intent);
                break;
            case R.id.btu_note:
                intent.putExtra("intent","Note");
                startActivity(intent);
                break;
        }
    }
}
