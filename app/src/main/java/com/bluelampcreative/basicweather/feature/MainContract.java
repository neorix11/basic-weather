package com.bluelampcreative.basicweather.feature;

public interface MainContract {

    public interface View {

    }

    public interface Presenter {

        void getWeatherClick(String city, String state);
    }
}
