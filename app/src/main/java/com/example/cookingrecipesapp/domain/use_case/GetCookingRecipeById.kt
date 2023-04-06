package com.example.cookingrecipesapp.domain.use_case

import com.example.cookingrecipesapp.domain.model.Meal
import com.example.cookingrecipesapp.domain.repository.CookingRecipesRepository
import kotlinx.coroutines.flow.Flow

class GetCookingRecipeById (private val repository: CookingRecipesRepository){

    operator fun invoke(id: String) : Flow<Meal> {
        return repository.getCookingRecipeById(id = id)
    }
}