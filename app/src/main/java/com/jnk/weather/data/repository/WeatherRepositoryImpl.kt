package com.jnk.weather.data.repository

import com.jnk.weather.data.remote.WeatherService
import com.jnk.weather.data.remote.mapper.toWeatherInfo
import com.jnk.weather.domain.repository.WeatherRepository
import com.jnk.weather.domain.util.Resource
import com.jnk.weather.domain.util.catch
import com.jnk.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> = catch {
        weatherService.getWeatherData(lat = lat, long = long).toWeatherInfo()
    }
}