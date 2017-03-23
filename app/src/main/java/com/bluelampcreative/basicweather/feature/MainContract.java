package com.bluelampcreative.basicweather.feature;

public interface MainContract {

    interface View {

    }

    interface Presenter {

        void getWeatherClick(String city, String state);
    }
}
