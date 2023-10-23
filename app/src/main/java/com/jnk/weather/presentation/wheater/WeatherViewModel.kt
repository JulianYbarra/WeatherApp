package com.jnk.weather.presentation.wheater

import androidx.lifecycle.viewModelScope
import com.jnk.weather.domain.location.LocationTracker
import com.jnk.weather.domain.repository.WeatherRepository
import com.jnk.weather.domain.util.Resource
import com.jnk.weather.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker
) : BaseViewModel<WeatherState>(WeatherState()) {

    fun loadWeatherInfo() {
        viewModelScope.launch {
            setState { state ->
                state.copy(
                    isLoading = true,
                    error = null
                )
            }

            locationTracker.getCurrentLocation()?.let { location ->
                when (val result = weatherRepository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Error -> {
                        setState { state ->
                            state.copy(
                                isLoading = false,
                                weatherInfo = null,
                                error = result.message
                            )
                        }
                    }

                    is Resource.Success -> {
                        setState { state ->
                            state.copy(
                                isLoading = false,
                                weatherInfo = result.data,
                                error = null
                            )
                        }
                    }
                }
            } ?: kotlin.run {
                setState { state ->
                    state.copy(
                        isLoading = false,
                        error = "No location found"
                    )
                }
            }
        }
    }
}