package com.bluelampcreative.basicweather.feature;

import com.bluelampcreative.basicweather.models.ForecastDay;

import java.util.List;

public interface MainContract {

    interface View {
        void updateWeatherList(List<ForecastDay> weatherList);
    }

    interface Presenter {

        void registerView(View view);

        void getWeatherClick(String city, String state);
    }
}
