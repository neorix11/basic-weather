package com.bluelampcreative.basicweather.services;


import com.bluelampcreative.basicweather.models.ForecastDay;
import com.bluelampcreative.basicweather.models.WeatherForecast;
import com.bluelampcreative.basicweather.retrofit.WeatherAPI;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class WeatherService {

    private WeatherAPI weatherAPI;

    @Inject
    public WeatherService(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    public Observable<List<ForecastDay>> getWetherForecast(String state, String city) {

        return weatherAPI.getWeather(state, city)
                .map(new Func1<WeatherForecast, List<ForecastDay>>() {
                    @Override
                    public List<ForecastDay> call(WeatherForecast weatherForecast) {
                        return weatherForecast
                                .getForecast()
                                .getTxt_forecast()
                                .getForecastday();
                    }
                });
    }
}
