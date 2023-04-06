package com.example.cookingrecipesapp.data.remote.response
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean? = null
) {
    class Success<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(data: T? = null, message: String? = null) : Resource<T>(data = data, message = message)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
}
