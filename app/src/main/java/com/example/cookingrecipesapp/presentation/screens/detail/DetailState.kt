package com.example.cookingrecipesapp.presentation.screens.detail

import com.example.cookingrecipesapp.domain.model.Meal

data class DetailState(
    val kitchenRecipe: Meal = Meal(),
)
