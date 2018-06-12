package com.example.dahaka.myweather.mvp.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.dahaka.myweather.mvp.model.WeatherData

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface WeatherView : BaseView {

    fun updateWeather(weatherData: WeatherData)

    fun showError(error: String)

    override fun showProgressBar()

    override fun hideProgressBar()
}