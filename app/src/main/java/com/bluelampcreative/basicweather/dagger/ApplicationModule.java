package com.bluelampcreative.basicweather.dagger;

import android.app.Application;
import android.content.Context;

import com.bluelampcreative.basicweather.feature.MainContract;
import com.bluelampcreative.basicweather.feature.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seanlarson on 3/22/17.
 */

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context getContext(){
        return application.getApplicationContext();
    }

    @Provides
    MainContract.Presenter getMainPresenter() {
        return new MainPresenter();
    }

}
