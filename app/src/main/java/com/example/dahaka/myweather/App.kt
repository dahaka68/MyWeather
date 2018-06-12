package com.example.dahaka.myweather

import android.app.Application
import com.example.dahaka.myweather.dagger.injection.component.AppComponent
import com.example.dahaka.myweather.dagger.injection.component.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.create()
    }
}