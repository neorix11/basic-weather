package com.bluelampcreative.basicweather.retrofit;

import com.bluelampcreative.basicweather.models.WeatherForecast;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface WeatherRetrofitService {

    @GET("/api/{key}/forecast/q/{state}/{city}.json")
    Observable<Response<WeatherForecast>> getWeather(@Path("key") String key,
                                                     @Path("state") String state,
                                                     @Path("city") String city);
}
