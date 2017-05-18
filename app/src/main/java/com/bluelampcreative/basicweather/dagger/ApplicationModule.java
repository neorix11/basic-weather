package com.bluelampcreative.basicweather.dagger;

import android.app.Application;
import android.content.Context;

import com.bluelampcreative.basicweather.R;
import com.bluelampcreative.basicweather.feature.MainContract;
import com.bluelampcreative.basicweather.feature.MainPresenter;
import com.bluelampcreative.basicweather.retrofit.WeatherAPI;
import com.bluelampcreative.basicweather.retrofit.WeatherRetrofitService;
import com.bluelampcreative.basicweather.services.WeatherService;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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


    /**
     * If you need to provide the authentication via headers you must first build an
     * OkHttpClient to hold the headers and then deliver it to retrofit.
     *
     * @return
     */
    @Provides
    @Singleton
    OkHttpClient getOkHttpClient() {
        //We want to log the network activity for debugging here so we don't have to do inline.
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        Interceptor headers = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request originalRequest = chain.request();

                Request newRequest = originalRequest.newBuilder()
                        .addHeader("Authorization", "<some auth key>")
                        //add other headers or methods if necessary here...
                        .build();

                return chain.proceed(newRequest);
            }
        };

        //Create the client
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(headers)
                .build();
    }

    @Singleton
    @Provides
    Retrofit getretrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
