package com.bluelampcreative.basicweather.feature;

import com.bluelampcreative.basicweather.core.BasePresenter;
import com.bluelampcreative.basicweather.models.ForecastDay;
import com.bluelampcreative.basicweather.services.WeatherService;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private WeatherService weatherService;

    @Inject
    public MainPresenter(WeatherService weatherService) {
        super(MainContract.View.class);
        this.weatherService = weatherService;
    }

    @Override
    public void updateView() {

    }

    @Override
    public void getWeatherClick(String city, String state) {
        weatherService.getWetherForecast(state, city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ForecastDay>>() {
                    @Override
                    public void call(List<ForecastDay> forecastDays) {

                    }
                });
    }

}
