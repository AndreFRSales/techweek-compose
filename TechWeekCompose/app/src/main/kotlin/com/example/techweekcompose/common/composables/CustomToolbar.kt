package com.example.techweekcompose.common.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.techweekcompose.theme.PokemonTheme

@Composable
fun CustomToolbar(modifier: Modifier = Modifier, text: String, onBackButtonClick: () -> Unit) {
    TopAppBar(modifier = modifier, title = { Text(text = text) }, navigationIcon = {
        IconButton(onClick = {
            onBackButtonClick()
        }) {
            Icon(Icons.Filled.ArrowBack, "backIcon")
        }
    }, backgroundColor = PokemonTheme.colors.primary)
}