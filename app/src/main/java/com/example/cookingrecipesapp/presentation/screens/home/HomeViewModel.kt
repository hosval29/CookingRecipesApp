package com.example.cookingrecipesapp.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingrecipesapp.core.utils.UiEvent
import com.example.cookingrecipesapp.core.utils.UiText
import com.example.cookingrecipesapp.data.remote.response.Resource
import com.example.cookingrecipesapp.domain.use_case.CookingRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cookingRecipesUseCase: CookingRecipesUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getAllKitchenRecipes()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
                onSearch()
            }
            is HomeEvent.OnNavigateDetail -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(event.meal.idMeal?:""))
                }
            }
        }
    }

    private fun onSearch() {
        cookingRecipesUseCase.searchCookingRecipes(state.query)
            .onEach { kitchenRecipes ->
                state = state.copy(
                    kitchenRecipes = kitchenRecipes
                )
            }.launchIn(viewModelScope)
    }

    private fun getAllKitchenRecipes() {
        viewModelScope.launch {
            cookingRecipesUseCase.getAllCookingRecipes()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                kitchenRecipes = resource.data ?: emptyList()
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                kitchenRecipes = resource.data ?: emptyList()
                            )
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    message = UiText.DynamicString("${resource.message}")
                                )
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true,
                                kitchenRecipes = resource.data ?: emptyList(),
                            )
                        }
                    }
                }.launchIn(this)
        }
    }
}