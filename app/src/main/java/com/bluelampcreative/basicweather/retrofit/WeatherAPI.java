package com.bluelampcreative.basicweather.retrofit;


import com.bluelampcreative.basicweather.models.WeatherForecast;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherAPI {

    private WeatherRetrofitService weatherRetrofitService;
    private String key;

    public WeatherAPI(WeatherRetrofitService weatherRetrofitService, String key) {
        this.weatherRetrofitService = weatherRetrofitService;
        this.key = key;
    }

    public Observable<WeatherForecast> getWeather(String state, String city) {
        return weatherRetrofitService.getWeather(key, state, city)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response<WeatherForecast>, WeatherForecast>() {
                    @Override
                    public WeatherForecast call(Response<WeatherForecast> weatherForecastResponse) {
                        return weatherForecastResponse.body();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Timber.e("There was an error fetching the weather");
                    }
                });

    }
}
