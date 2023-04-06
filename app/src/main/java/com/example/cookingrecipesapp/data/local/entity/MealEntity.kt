package com.example.cookingrecipesapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class MealEntity(
    @PrimaryKey val idMeal: String,
    @ColumnInfo("ingredients") val ingredients: String = "",
    val latitude: String,
    val longitude: String,
    @ColumnInfo("measures") val measures: String = "",
    val strArea: String,
    val strCategory: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
    val strSource: String,
    val strTags: String,
    val strYoutube: String
)