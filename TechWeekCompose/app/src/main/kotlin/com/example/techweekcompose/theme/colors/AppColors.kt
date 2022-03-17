package com.example.techweekcompose.theme.colors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    primaryDark: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    error: Color,
    divider: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var primaryDark by mutableStateOf(primaryDark)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var background by mutableStateOf(background)
        private set
    var error by mutableStateOf(error)
        private set
    var divider by mutableStateOf(divider)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        primaryDark: Color = this.primaryDark,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        error: Color = this.error,
        textSecondary: Color = this.textSecondary,
        background: Color = this.background,
        divider: Color = this.divider,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary, primaryDark, secondary, textPrimary, error, textSecondary, background, divider, isLight
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        primaryDark = other.primaryDark
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        secondary = other.secondary
        background = other.background
        error = other.error
        divider = other.divider
    }

}