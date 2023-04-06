package com.example.cookingrecipesapp.presentation.screens.map

sealed class MapEvent {
    data class OnsetLatLng(val latitude: String, val longitude: String) : MapEvent()
}