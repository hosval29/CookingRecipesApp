package com.example.cookingrecipesapp.domain.use_case

import com.example.cookingrecipesapp.data.remote.response.Resource
import com.example.cookingrecipesapp.domain.model.Meal
import com.example.cookingrecipesapp.domain.repository.CookingRecipesRepository
import kotlinx.coroutines.flow.Flow

class GetAllCookingRecipes (private val repository: CookingRecipesRepository){

    operator fun invoke() : Flow<Resource<List<Meal>>>{
        return repository.getCookingRecipes()
    }
}