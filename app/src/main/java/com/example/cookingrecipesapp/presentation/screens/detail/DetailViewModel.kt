package com.example.cookingrecipesapp.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.example.cookingrecipesapp.core.utils.UiEvent
import com.example.cookingrecipesapp.domain.use_case.CookingRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cookingRecipesUseCase: CookingRecipesUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnNavigateMap -> {
                viewModelScope.launch {
                    val json = Gson().toJson(event.meal)
                    _uiEvent.send(UiEvent.Navigate(json))
                }
            }
            is DetailEvent.OnGetKitchenRecipeById -> {
                getKitchenRecipeById(event.id)
            }
        }
    }

    private fun getKitchenRecipeById(id: String) {
        cookingRecipesUseCase.getCookingRecipeById(id)
            .onEach { kitchenRecipes ->
                state = state.copy(
                    kitchenRecipe = kitchenRecipes
                )
            }.launchIn(viewModelScope)
    }

}