package com.jnk.weather.presentation.wheater

import com.jnk.weather.domain.weather.WeatherInfo
import com.jnk.weather.presentation.base.State

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading : Boolean = false,
    val error : String? = null
) : State