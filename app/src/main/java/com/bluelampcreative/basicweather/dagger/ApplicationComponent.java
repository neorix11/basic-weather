package com.bluelampcreative.basicweather.dagger;

import android.content.Context;

import com.bluelampcreative.basicweather.feature.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by seanlarson on 3/22/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context provideContext();

    void inject(MainActivity mainActivity);
}
