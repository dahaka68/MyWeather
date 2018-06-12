package com.example.dahaka.myweather.network

import com.example.dahaka.myweather.mvp.model.WeatherData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {
    @GET("weather")
    fun queryWeather(
            @Query("q") city: String,
            @Query("appid") appId: String
    ): Observable<WeatherData>
}