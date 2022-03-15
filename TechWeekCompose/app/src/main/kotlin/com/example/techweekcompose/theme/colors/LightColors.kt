package com.example.techweekcompose.theme.colors

import androidx.compose.ui.graphics.Color

private val colorLightPrimary = Color(0xFFFFFF00)
private val colorLightSecondary = Color(0xFF8C6D62)
private val colorLightTextPrimary = Color(0xFF000000)
private val colorLightTextSecondary = Color(0xFFFFFFFF)
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightError = Color(0xFFD62222)

fun lightColors(
    primary: Color = colorLightPrimary,
    secondary: Color = colorLightSecondary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError
) = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = true
)