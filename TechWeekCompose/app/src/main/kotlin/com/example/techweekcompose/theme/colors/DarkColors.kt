package com.example.techweekcompose.theme.colors

import androidx.compose.ui.graphics.Color

private val colorDarkPrimary = Color(0xFFFFFF00)
private val colorDarkPrimaryDark = Color(0xFFD5A100)
private val colorDarkSecondary = Color(0xFF8C6D62)
private val colorDarkTextPrimary = Color(0xFF000000)
private val colorDarkTextSecondary = Color(0xFFFFFFFF)
private val colorDarkBackground = Color(0xFFFFFFFF)
private val colorDarkError = Color(0xFFD62222)
private val colorDarkDivider = Color(0xFFBDBDBD)

fun darkColors(
    primary: Color = colorDarkPrimary,
    primaryDark: Color = colorDarkPrimaryDark,
    secondary: Color = colorDarkSecondary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    error: Color = colorDarkError,
    divider: Color = colorDarkDivider
) = AppColors(
    primary = primary,
    primaryDark = primaryDark,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    divider = divider,
    isLight = false
)