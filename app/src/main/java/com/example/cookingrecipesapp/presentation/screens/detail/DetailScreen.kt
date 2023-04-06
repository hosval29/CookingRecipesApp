package com.example.cookingrecipesapp.presentation.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cookingrecipesapp.R
import com.example.cookingrecipesapp.presentation.screens.detail.components.CardKitchenRecipe

@Composable
fun DetailScreen(
    idMeal: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigateMap: (String, String) -> Unit
) {
    val state = viewModel.state
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(idMeal) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.onEvent(DetailEvent.OnGetKitchenRecipeById(idMeal))
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val (
            kitchenRecipeItem,
            mapButton,
            tittleInstructions,
            descriptionInstructions
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(16.dp)
        val startGuideline = createGuidelineFromStart(24.dp)
        val endGuideline = createGuidelineFromEnd(24.dp)

        CardKitchenRecipe(
            meal = state.kitchenRecipe,
            modifier = Modifier.constrainAs(kitchenRecipeItem) {
                top.linkTo(topGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )
        Row(
            modifier = Modifier
                .clickable {
                    onNavigateMap(
                        state.kitchenRecipe.latitude ?: "0",
                        state.kitchenRecipe.longitude ?: "0"
                    )
                }
                .constrainAs(mapButton) {
                    top.linkTo(kitchenRecipeItem.bottom, margin = 12.dp)
                    start.linkTo(startGuideline)
                }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "ICON_MAP",
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Ver receta en el mapa",
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = "Intrucciones de la receta",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(tittleInstructions) {
                top.linkTo(mapButton.bottom, margin = 12.dp)
                start.linkTo(startGuideline)
            }
        )

        Text(
            text = "${state.kitchenRecipe.strInstructions}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify,
            modifier = Modifier.constrainAs(descriptionInstructions) {
                top.linkTo(tittleInstructions.bottom, margin = 12.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )
    }

}