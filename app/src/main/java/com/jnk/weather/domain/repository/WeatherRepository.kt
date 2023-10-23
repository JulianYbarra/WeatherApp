package com.jnk.weather.domain.repository

import com.jnk.weather.domain.util.Resource
import com.jnk.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat : Double,long : Double) : Resource<WeatherInfo>
}