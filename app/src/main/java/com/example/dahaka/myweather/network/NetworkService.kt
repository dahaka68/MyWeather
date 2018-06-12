package com.example.dahaka.myweather.network

import com.example.dahaka.myweather.mvp.model.WeatherData
import io.reactivex.Observable
import javax.inject.Inject

class NetworkService @Inject constructor(private val networkInterface: NetworkInterface) {

    fun getWeather(city: String, apiKey: String): Observable<WeatherData> {
        return this.networkInterface.queryWeather(city, apiKey)
    }
}