package com.jnk.weather.domain.weather

data class WeatherInfo(
    val weatherDataPerDay : Map<Int,List<Weather>>,
    val currentWeather : Weather?
)