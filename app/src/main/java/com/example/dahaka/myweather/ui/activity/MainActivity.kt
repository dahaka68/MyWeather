package com.example.dahaka.myweather.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dahaka.myweather.R
import com.example.dahaka.myweather.mvp.model.WeatherData
import com.example.dahaka.myweather.mvp.presenter.WeatherPresenter
import com.example.dahaka.myweather.mvp.view.WeatherView
import com.example.dahaka.myweather.utils.EMPTY
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("NOTHING_TO_INLINE")
class MainActivity : MvpAppCompatActivity(), WeatherView {

    @InjectPresenter
    lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.loadWeather(etCity)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun updateWeather(weatherData: WeatherData) {
        fillFields(weatherData)
    }

    private fun fillFields(weatherData: WeatherData) {
        if (weatherData.name != EMPTY) {
            val tempCel: Double? = weatherData.main?.temp?.minus(273.15)
            val temp = "${tempCel?.round()} °C"
            val windSpeed = "${getString(R.string.wind)} ${weatherData.wind?.speed} m/s"
            val cloud = "${getString(R.string.clouds)} ${weatherData.clouds.toString().filter { it.isDigit() }} %"
            val press = "${getString(R.string.pressure)} ${weatherData.main?.pressure} hPa"
            temperature.text = temp
            wind.text = windSpeed
            name.text = weatherData.name.toString()
            clouds.text = cloud
            pressure.text = press
        } else {
            temperature.text = ""
            wind.text = ""
            name.text = ""
            clouds.text = ""
            pressure.text = ""
        }
    }

    override fun showError(error: String) {
        toast(getString(R.string.unknown_error))
    }

    private inline fun Double.round(): Double = Math.round(this * 100.0) / 100.0

    private fun Activity.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}