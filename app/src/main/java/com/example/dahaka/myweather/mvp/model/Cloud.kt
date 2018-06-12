package com.example.dahaka.myweather.mvp.model

import com.google.gson.annotations.SerializedName

data class Cloud(
        @field:SerializedName("all")
        val all: Int? = null
)