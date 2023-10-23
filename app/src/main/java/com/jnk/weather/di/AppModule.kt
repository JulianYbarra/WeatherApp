package com.jnk.weather.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jnk.weather.data.remote.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @ApiUrl
    fun getApiUrl() : String = "https://api.open-meteo.com/"

    @Provides
    @Singleton
    fun provideWeatherService(@ApiUrl apiUrl: String) : WeatherService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

}