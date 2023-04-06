package com.example.cookingrecipesapp.domain.use_case

import com.example.cookingrecipesapp.domain.model.Meal
import com.example.cookingrecipesapp.domain.repository.CookingRecipesRepository
import kotlinx.coroutines.flow.Flow

class SearchCookingRecipes(private val repository: CookingRecipesRepository) {

    operator fun invoke(query: String): Flow<List<Meal>> {
        return repository.searchCookingRecipes(query)
    }
}