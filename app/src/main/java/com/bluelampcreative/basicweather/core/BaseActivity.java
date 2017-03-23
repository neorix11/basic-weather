package com.bluelampcreative.basicweather.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bluelampcreative.basicweather.BasicWeather;
import com.bluelampcreative.basicweather.dagger.ApplicationComponent;

/**
 * Created by seanlarson on 3/22/17.
 */

public abstract class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ApplicationComponent getApplicationComponent() {
        return ((BasicWeather) getApplication()).getApplicationComponent();
    }
}
