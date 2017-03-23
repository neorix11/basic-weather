package com.bluelampcreative.basicweather.feature;

import com.bluelampcreative.basicweather.core.BasePresenter;

/**
 * Created by seanlarson on 3/22/17.
 */

public interface MainContract {

    public interface View {

    }

    public interface Presenter {

        void getWeatherClick(String zipCode);
    }
}
