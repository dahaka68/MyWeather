package com.example.dahaka.myweather.dagger.module

import com.example.dahaka.myweather.network.NetworkInterface
import com.example.dahaka.myweather.network.NetworkService
import com.example.dahaka.myweather.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [(RetrofitModule::class)])
class ApiModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }
}