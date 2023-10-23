package com.jnk.weather.data.remote.mapper

import com.jnk.weather.data.remote.model.WeatherApiModel
import com.jnk.weather.data.remote.model.WeatherDataApiModel
import com.jnk.weather.domain.weather.Weather
import com.jnk.weather.domain.weather.WeatherInfo
import com.jnk.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: Weather
)

fun WeatherDataApiModel.toWeather(): Map<Int, List<Weather>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCodes = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data =
            Weather(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCodes)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map {
            it.data
        }
    }
}

fun WeatherApiModel.toWeatherInfo() : WeatherInfo {
    val weatherData = weatherData.toWeather()
    val now = LocalDateTime.now()
    val currentWeather = weatherData[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherData,
        currentWeather = currentWeather
    )
}