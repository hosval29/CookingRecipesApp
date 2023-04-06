package com.example.cookingrecipesapp.presentation.screens.detail

import com.example.cookingrecipesapp.domain.model.Meal


sealed class DetailEvent {
    data class OnNavigateMap(val meal : Meal) : DetailEvent()
    data class OnGetKitchenRecipeById (val id : String) : DetailEvent()
}