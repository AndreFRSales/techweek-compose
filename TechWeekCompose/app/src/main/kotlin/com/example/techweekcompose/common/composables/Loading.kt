package com.example.techweekcompose.common.composables

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.techweekcompose.theme.PokemonTheme

@Composable
fun CustomLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier, color = PokemonTheme.colors.secondary)
}
