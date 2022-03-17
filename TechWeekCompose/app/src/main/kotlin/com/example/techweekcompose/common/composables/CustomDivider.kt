package com.example.techweekcompose.common.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.techweekcompose.theme.PokemonTheme

@Composable
fun CustomDivider() {
    Divider(
        color = PokemonTheme.colors.divider,
        modifier = Modifier.padding(start = PokemonTheme.dimensions.medium)
    )
}