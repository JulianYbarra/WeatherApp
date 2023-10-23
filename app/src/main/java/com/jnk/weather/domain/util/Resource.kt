package com.jnk.weather.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}


inline fun <T> catch(action : () -> T) : Resource<T>{
    return try {
        Resource.Success(action())
    } catch (e: Exception){
        Resource.Error(e.message ?: "An unexpected error occurred")
    }
}