package com.bluelampcreative.basicweather.feature;

import com.bluelampcreative.basicweather.core.BasePresenter;

import timber.log.Timber;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    public MainPresenter() {
        super(MainContract.View.class);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void getWeatherClick(String city, String state) {
        Timber.e("Your location is %s, %s", city, state);
    }
}
