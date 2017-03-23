package com.bluelampcreative.basicweather;

import android.app.Application;

import com.bluelampcreative.basicweather.dagger.ApplicationComponent;
import com.bluelampcreative.basicweather.dagger.ApplicationModule;
import com.bluelampcreative.basicweather.dagger.DaggerApplicationComponent;

import timber.log.Timber;

public class BasicWeather extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}