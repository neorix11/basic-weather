package com.bluelampcreative.basicweather.models;

import java.util.List;

import lombok.Data;

@Data
public class TextForecast {
    private String date;
    private List<ForecastDay> forecastday;
}
