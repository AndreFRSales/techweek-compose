package com.example.techweekcompose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.decode.SvgDecoder
import com.example.techweekcompose.theme.colors.AppColors
import com.example.techweekcompose.theme.colors.LocalColors
import com.example.techweekcompose.theme.colors.darkColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController

object PokemonTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
    val iconDimensions: IconDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalIconDimensions.current
}

@Composable
fun PokemonTheme(
    colors: AppColors = PokemonTheme.colors,
    typography: Typography = PokemonTheme.typography,
    dimensions: Dimensions = PokemonTheme.dimensions,
    iconDimensions: IconDimensions = PokemonTheme.iconDimensions,
    darkColors: AppColors? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    StatusBarColor(darkTheme = darkTheme)
    val currentColor = remember { if (darkColors != null && darkTheme) darkColors() else colors }
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(currentColor) }
    val imageLoader = createImageLoaderProvider()
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalIconDimensions provides iconDimensions,
        LocalImageLoader provides imageLoader
    ) {
        ProvideTextStyle(value = typography.h1, content = content)
    }
}

@Composable
fun StatusBarColor(darkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setStatusBarColor(color = PokemonTheme.colors.secondary)
    } else {
        systemUiController.setStatusBarColor(color = PokemonTheme.colors.primaryDark)
    }
}

@Composable
fun createImageLoaderProvider() = ImageLoader.Builder(LocalContext.current)
    .componentRegistry {
        add(SvgDecoder(LocalContext.current))
    }
    .build()