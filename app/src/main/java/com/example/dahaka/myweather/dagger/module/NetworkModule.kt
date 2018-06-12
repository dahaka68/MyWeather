package com.example.dahaka.myweather.dagger.module

import com.example.dahaka.myweather.network.NetworkInterface
import com.example.dahaka.myweather.network.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ApiModule::class)])
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkService(networkInterface: NetworkInterface): NetworkService {
        return NetworkService(networkInterface)
    }
}