package com.jnk.weather.domain.weather

import java.time.LocalDateTime

data class Weather(
    val time : LocalDateTime,
    val temperatureCelsius : Double,
    val pressure : Double,
    val windSpeed : Double,
    val humidity : Double,
    val weatherType: WeatherType
)