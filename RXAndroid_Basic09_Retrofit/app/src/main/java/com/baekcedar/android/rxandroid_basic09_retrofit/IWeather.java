package com.baekcedar.android.rxandroid_basic09_retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by HM on 2016-11-04.
 */
/*
http://api.openweathermap.org/data/2.5/weather?q=Newyork&APPID=11c7f631cd74614447645fc773e2a1a7
*/
public interface IWeather {

    @GET("/data/2.5/weather")
    Observable<Data> getData(@Query("q") String cityName, @Query("APPID") String API_KEY);
}
