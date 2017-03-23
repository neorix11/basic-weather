package com.bluelampcreative.basicweather.feature;

import com.bluelampcreative.basicweather.core.BasePresenter;

import timber.log.Timber;

/**
 * Created by seanlarson on 3/22/17.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    public MainPresenter() {
        super(MainContract.View.class);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void getWeatherClick(String zipCode) {
        Timber.e("ZIP IS: %s", zipCode);
    }
}
