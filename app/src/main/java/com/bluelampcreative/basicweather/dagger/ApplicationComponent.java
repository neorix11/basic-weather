package com.bluelampcreative.basicweather.dagger;

import android.content.Context;

import com.bluelampcreative.basicweather.feature.MainActivity;
import com.bluelampcreative.basicweather.retrofit.WeatherAPI;
import com.bluelampcreative.basicweather.retrofit.WeatherRetrofitService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context provideContext();

    WeatherRetrofitService provideWeatherService();
    WeatherAPI provideWeatherAPI();

    void inject(MainActivity mainActivity);
}
