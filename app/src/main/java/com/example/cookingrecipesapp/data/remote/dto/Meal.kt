package com.example.cookingrecipesapp.data.remote.dto

import com.squareup.moshi.Json

data class Meal(
    @field:Json(name = "dateModified")
    val dateModified: Any? = Any(),
    @field:Json(name = "idMeal")
    val idMeal: String? = "",
    @field:Json(name = "ingredients")
    val ingredients: String? = "",
    @field:Json(name = "latitude")
    val latitude: String? = "",
    @field:Json(name = "longitude")
    val longitude: String? = "",
    @field:Json(name = "measures")
    val measures: String? = "",
    @field:Json(name = "strArea")
    val strArea: String? = "",
    @field:Json(name = "strCategory")
    val strCategory: String? = "",
    @field:Json(name = "strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: Any? = Any(),
    @field:Json(name = "strDrinkAlternate")
    val strDrinkAlternate: Any? = Any(),
    @field:Json(name = "strImageSource")
    val strImageSource: Any? = Any(),
    @field:Json(name = "strInstructions")
    val strInstructions: String? = "",
    @field:Json(name = "strMeal")
    val strMeal: String? = "",
    @field:Json(name = "strMealThumb")
    val strMealThumb: String? = "",
    @field:Json(name = "strSource")
    val strSource: String? = "",
    @field:Json(name = "strTags")
    val strTags: String? = "",
    @field:Json(name = "strYoutube")
    val strYoutube: String? = ""
)