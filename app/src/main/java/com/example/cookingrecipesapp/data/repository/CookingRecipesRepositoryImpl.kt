package com.example.cookingrecipesapp.data.repository

import android.util.Log
import com.example.cookingrecipesapp.data.local.dao.MealDao
import com.example.cookingrecipesapp.data.mapper.toMeal
import com.example.cookingrecipesapp.data.mapper.toMealEntity
import com.example.cookingrecipesapp.data.remote.api.CookingRecipesApi
import com.example.cookingrecipesapp.data.remote.response.Resource
import com.example.cookingrecipesapp.domain.model.Meal
import com.example.cookingrecipesapp.domain.repository.CookingRecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CookingRecipesRepositoryImpl(
    private val cookingRecipesApi: CookingRecipesApi,
    private val mealDao: MealDao
) :
    CookingRecipesRepository {
    override fun getCookingRecipes(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        val meals = mealDao.getAllMeals().map { it.toMeal() }
        emit(Resource.Loading(data = meals))

        try {
            val remoteMeals = cookingRecipesApi.getRecipesKitchen()
            val list = remoteMeals.toMealEntity()
            mealDao.deleteAllMeal()
            mealDao.insertAllMeal(list)
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, algo salió mal, vuelve a intentar.",
                    data = meals
                )
            )
            Log.d("mplTAG", e.toString())
            return@flow
        }

        val newMeals = mealDao.getAllMeals().map { it.toMeal() }
        emit(Resource.Success(data = newMeals))
    }

    override fun searchCookingRecipes(query: String): Flow<List<Meal>> = flow {
        val meals = mealDao.getAllMealsByQuery(query).map { it.toMeal() }
        emit(meals)
    }

    override fun getCookingRecipeById(id: String): Flow<Meal> = flow {
        val meal = mealDao.getMealsById(id).toMeal()
        emit(meal)
    }
}