package com.bluelampcreative.basicweather.models;

import lombok.Data;

@Data
public class ForecastDay {
    private int period;
    private String icon;
    private String icon_url;
    private String title;
    private String fcttext;
    private String fcttext_metric;
    private String pop;
}
