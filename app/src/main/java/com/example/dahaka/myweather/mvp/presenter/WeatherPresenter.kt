package com.example.dahaka.myweather.mvp.presenter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dahaka.myweather.App
import com.example.dahaka.myweather.R.string.unknown_error
import com.example.dahaka.myweather.mvp.model.WeatherData
import com.example.dahaka.myweather.mvp.view.WeatherView
import com.example.dahaka.myweather.network.NetworkService
import com.example.dahaka.myweather.utils.API_ID
import com.example.dahaka.myweather.utils.EMPTY
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class WeatherPresenter : MvpPresenter<WeatherView>() {
    @Inject
    lateinit var networkService: NetworkService

    init {
        App.appComponent.inject(this)
    }

    private fun EditText.emitter(): Observable<String> {
        return Observable.create({
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    viewState.showProgressBar()
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    it.onNext(s.toString())
                }

                override fun afterTextChanged(s: Editable) {}
            }
            this.addTextChangedListener(textWatcher)
            it.setCancellable { this.removeTextChangedListener(textWatcher) }
        })
    }

    private fun request(city: String): Observable<WeatherData> {
        return networkService
                .getWeather(city.trim(), API_ID)
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { WeatherData(name = EMPTY) }
                .doAfterTerminate { viewState.hideProgressBar() }
    }

    fun loadWeather(city: EditText) {
        val observable: Observable<WeatherData> = city.emitter()
                .debounce(400, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap { text -> request(text) }
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(
                { weatherData -> viewState.updateWeather(weatherData) },
                { viewState.showError(unknown_error.toString()) })
    }
}