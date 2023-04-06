package com.example.cookingrecipesapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookingrecipesapp.R
import com.example.cookingrecipesapp.core.utils.UiEvent
import com.example.cookingrecipesapp.domain.model.Meal
import com.example.cookingrecipesapp.presentation.components.ShimmerKitchenRecipeItem
import com.example.cookingrecipesapp.presentation.screens.home.components.KitchenRecipeItem
import com.example.cookingrecipesapp.presentation.screens.home.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    snackBarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateDetail: (String) -> Unit
) {

    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()
                }
                is UiEvent.Navigate -> {
                    onNavigateDetail(event.route)
                }
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            SearchTextField(
                text = state.query,
                onValueChange = {
                    viewModel.onEvent(HomeEvent.OnQueryChange(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(
            items = state.kitchenRecipes,
            key = { item: Meal -> item.idMeal ?: "" }
        ) { meal ->
            KitchenRecipeItem(
                meal = meal,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.onEvent(HomeEvent.OnNavigateDetail(it))
            }
        }
        if (state.isLoading) {
            items(10) {
                ShimmerKitchenRecipeItem()
            }
        }
    }
    if (!state.isLoading && state.kitchenRecipes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.empty_list),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF004A50)
            )
        }
    }
}