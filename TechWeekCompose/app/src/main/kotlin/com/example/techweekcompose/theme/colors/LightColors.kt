package com.example.techweekcompose.theme.colors

import androidx.compose.ui.graphics.Color

private val colorLightPrimary = Color(0xFFFFE082)
private val colorLightPrimaryDark = Color(0xFFD5A100)
private val colorLightSecondary = Color(0xFF0A285F)
private val colorLightTextPrimary = Color(0xFF000000)
private val colorLightTextSecondary = Color(0xFFFFFFFF)
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightError = Color(0xFFD62222)
private val colorLightDivider = Color(0xFFBDBDBD)

fun lightColors(
    primary: Color = colorLightPrimary,
    primaryDark: Color = colorLightPrimaryDark,
    secondary: Color = colorLightSecondary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError,
    divider: Color = colorLightDivider
) = AppColors(
    primary = primary,
    primaryDark = primaryDark,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    divider = divider,
    isLight = true
)