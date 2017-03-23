package com.bluelampcreative.basicweather.dagger;

import android.app.Application;
import android.content.Context;

import com.bluelampcreative.basicweather.R;
import com.bluelampcreative.basicweather.feature.MainContract;
import com.bluelampcreative.basicweather.feature.MainPresenter;
import com.bluelampcreative.basicweather.retrofit.WeatherAPI;
import com.bluelampcreative.basicweather.retrofit.WeatherRetrofitService;
import com.bluelampcreative.basicweather.services.WeatherService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context getContext() {
        return application.getApplicationContext();
    }

    @Provides
    MainContract.Presenter getMainPresenter(WeatherService weatherService) {
        return new MainPresenter(weatherService);
    }

    @Provides
    WeatherRetrofitService getWeatherRetrofitService(Retrofit retrofit) {
        return retrofit.create(WeatherRetrofitService.class);
    }

    @Provides
    WeatherService getWeatherService(WeatherAPI weatherAPI) {
        return new WeatherService(weatherAPI);
    }

    @Provides
    WeatherAPI getWeatherAPI(WeatherRetrofitService weatherRetrofitService, @Named("weather_key") String key) {
        return new WeatherAPI(weatherRetrofitService, key);
    }

    @Named("weather_key")
    @Provides
    String getKey() {
        return application.getString(R.string.wunderground_key);
    }

    @Singleton
    @Provides
    Retrofit getretrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
