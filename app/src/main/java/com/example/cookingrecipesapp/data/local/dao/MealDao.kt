package com.example.cookingrecipesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookingrecipesapp.data.local.entity.MealEntity

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMeal(meal: List<MealEntity>)

    @Query("SELECT * FROM meal_table WHERE strMeal OR ingredients LIKE '%' || :query || '%'")
    suspend fun getAllMealsByQuery(query: String): List<MealEntity>

    @Query("SELECT * FROM meal_table WHERE idMeal LIKE '%' || :id || '%'")
    suspend fun getMealsById(id: String): MealEntity

    @Query("SELECT * FROM meal_table ORDER BY strMeal ASC")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("DELETE FROM meal_table")
    suspend fun deleteAllMeal()

}