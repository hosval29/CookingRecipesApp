package com.example.cookingrecipesapp.presentation.screens.home

import com.example.cookingrecipesapp.domain.model.Meal

data class HomeState(
    val kitchenRecipes: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
)
