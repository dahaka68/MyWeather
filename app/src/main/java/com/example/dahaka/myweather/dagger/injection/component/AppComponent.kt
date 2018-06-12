package com.example.dahaka.myweather.dagger.injection.component

import com.example.dahaka.myweather.dagger.module.NetworkModule
import com.example.dahaka.myweather.mvp.presenter.WeatherPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface AppComponent {

    fun inject(weatherPresenter: WeatherPresenter)
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun networkModule(networkModule: NetworkModule): Builder
    }
}