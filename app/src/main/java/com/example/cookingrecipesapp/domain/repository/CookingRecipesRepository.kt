package com.example.cookingrecipesapp.domain.repository

import com.example.cookingrecipesapp.data.remote.response.Resource
import com.example.cookingrecipesapp.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface CookingRecipesRepository {

    fun getCookingRecipes(): Flow<Resource<List<Meal>>>

    fun searchCookingRecipes(query: String): Flow<List<Meal>>

    fun getCookingRecipeById(id: String): Flow<Meal>
}