package com.example.cookingrecipesapp.presentation.screens.home

import com.example.cookingrecipesapp.domain.model.Meal


sealed class HomeEvent {
    data class OnQueryChange(val query: String) : HomeEvent()
    data class OnNavigateDetail(val meal : Meal) : HomeEvent()
}