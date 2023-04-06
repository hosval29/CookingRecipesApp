package com.example.cookingrecipesapp.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCompose(
    title: String = "",
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        },
        navigationIcon = {
            navigationIcon?.let { imageVector ->
                IconButton(
                    onClick = {
                        onNavigationClick?.let { onNavigationClick ->
                            onNavigationClick()
                        }
                    }
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF004A50),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        modifier = Modifier
            .shadow(elevation = 4.dp)
    )

}