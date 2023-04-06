package com.example.cookingrecipesapp.core.utils

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
}
