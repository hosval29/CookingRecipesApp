package com.example.cookingrecipesapp.data.remote.api

import com.example.cookingrecipesapp.data.remote.dto.MealsDto
import retrofit2.http.GET

interface CookingRecipesApi {
    @GET("b2847764-530e-4825-8d43-979370572cc0")
    suspend fun getRecipesKitchen(): MealsDto
}