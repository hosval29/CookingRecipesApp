package com.example.cookingrecipesapp.presentation.screens.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.cookingrecipesapp.R
import com.example.cookingrecipesapp.domain.model.Meal
import java.util.*

@Composable
fun CardKitchenRecipe(
    meal: Meal,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val (
                imageKitchenRecipe,
                columnKitchenRecipe
            ) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .crossfade(true)
                        .data(meal.strMealThumb)
                        .size(Size.ORIGINAL)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .build(),
                    filterQuality = FilterQuality.High
                ),
                contentDescription = meal.strMeal,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .constrainAs(imageKitchenRecipe) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .padding(16.dp)
                    .constrainAs(columnKitchenRecipe) {
                        bottom.linkTo(imageKitchenRecipe.bottom)
                        start.linkTo(imageKitchenRecipe.start)
                    }
            ) {
                Text(
                    text = meal.strMeal!!.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = Color(0xFF004A50),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${meal.strArea}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color(0xFF004A50),
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${meal.strCategory}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color(0xFF004A50),
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
