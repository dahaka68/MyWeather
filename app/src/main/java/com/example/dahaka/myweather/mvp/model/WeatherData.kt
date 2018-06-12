package com.example.dahaka.myweather.mvp.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
        @field:SerializedName("dt")
        val dt: Int? = null,

        @field:SerializedName("coord")
        val coord: Coordinate? = null,

        @field:SerializedName("weather")
        val weather: List<Weather?>? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("cod")
        val cod: Int? = null,

        @field:SerializedName("main")
        val main: Main? = null,

        @field:SerializedName("clouds")
        val clouds: Cloud? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("sys")
        val sys: Sys? = null,

        @field:SerializedName("base")
        val base: String? = null,

        @field:SerializedName("wind")
        val wind: Wind? = null
)