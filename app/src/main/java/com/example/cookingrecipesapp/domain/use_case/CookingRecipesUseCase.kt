package com.example.cookingrecipesapp.domain.use_case

data class CookingRecipesUseCase(
    val getAllCookingRecipes: GetAllCookingRecipes,
    val searchCookingRecipes: SearchCookingRecipes,
    val getCookingRecipeById: GetCookingRecipeById
)