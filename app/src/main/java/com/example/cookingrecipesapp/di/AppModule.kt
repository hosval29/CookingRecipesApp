package com.example.cookingrecipesapp.di

import android.app.Application
import androidx.room.Room
import com.example.cookingrecipesapp.BuildConfig
import com.example.cookingrecipesapp.data.local.database.CookingRecipeDataBase
import com.example.cookingrecipesapp.data.remote.api.CookingRecipesApi
import com.example.cookingrecipesapp.data.repository.CookingRecipesRepositoryImpl
import com.example.cookingrecipesapp.domain.repository.CookingRecipesRepository
import com.example.cookingrecipesapp.domain.use_case.CookingRecipesUseCase
import com.example.cookingrecipesapp.domain.use_case.GetAllCookingRecipes
import com.example.cookingrecipesapp.domain.use_case.GetCookingRecipeById
import com.example.cookingrecipesapp.domain.use_case.SearchCookingRecipes

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideKitchenRecipesApi(retrofit: Retrofit): CookingRecipesApi {
        return retrofit.create(CookingRecipesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKitchenRecipeDataBase(app: Application): CookingRecipeDataBase {
        return Room.databaseBuilder(
            app.applicationContext,
            CookingRecipeDataBase::class.java,
            "kitchen_recipe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideKitchenRecipesRepository(
        cookingRecipesApi: CookingRecipesApi,
        database: CookingRecipeDataBase
    ): CookingRecipesRepository {
        return CookingRecipesRepositoryImpl(cookingRecipesApi, database.mealDao)
    }

    @Provides
    @Singleton
    fun provideKitchenRecipesUseCases(repository: CookingRecipesRepository): CookingRecipesUseCase {
        return CookingRecipesUseCase(
            getAllCookingRecipes = GetAllCookingRecipes(repository = repository),
            searchCookingRecipes = SearchCookingRecipes(repository = repository),
            getCookingRecipeById = GetCookingRecipeById(repository = repository)
        )
    }
}