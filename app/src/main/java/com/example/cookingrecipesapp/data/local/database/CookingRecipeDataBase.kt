package com.example.cookingrecipesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookingrecipesapp.data.local.dao.MealDao
import com.example.cookingrecipesapp.data.local.entity.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 1
)
abstract class CookingRecipeDataBase : RoomDatabase() {
    abstract val mealDao: MealDao
}