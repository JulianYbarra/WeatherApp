package com.jnk.weather.data.remote

import com.jnk.weather.data.remote.model.WeatherApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v1/forecast?current=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&hourly=temperature_2m")
    suspend fun getWeatherData(
        @Query("latitude") lat : Double,
        @Query("longitude") long : Double,
    ) : WeatherApiModel
}