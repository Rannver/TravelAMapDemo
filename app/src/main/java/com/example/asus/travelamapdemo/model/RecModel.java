package com.example.asus.travelamapdemo.model;

import com.example.asus.travelamapdemo.contract.RecContract;

/**
 * Created by ASUS on 2017/7/22.
 */

public class RecModel {

    private RecContract.RecPresenter presenter;

    public RecModel(RecContract.RecPresenter presenter){
        this.presenter = presenter;
    }
}
