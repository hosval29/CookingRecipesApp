package com.example.cookingrecipesapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingrecipesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(id = R.string.search)
) {
    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF004A50),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF004A50),
                unfocusedBorderColor = Color(0xFF004A50),
                focusedLabelColor = Color.Black,
                cursorColor = Color(0xFF004A50)
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}