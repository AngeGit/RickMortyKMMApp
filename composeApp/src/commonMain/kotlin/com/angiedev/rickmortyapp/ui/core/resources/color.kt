package com.angiedev.rickmortyapp.ui.core.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val BackgroundPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) primaryBlack else primaryWhite

val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) tertiaryBlack else secondaryWhite

val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryBlack else tertiaryWhite

val DefaultTextColor
    @Composable
    get() = if(isSystemInDarkTheme()) Color.White else Color.Black

val PlaceholderBorderColor
    @Composable
    get() = if (isSystemInDarkTheme()) BrightGreen else Green

val PlaceholderShadowColor
    @Composable
    get() = if (isSystemInDarkTheme()) BrightGreen.copy(alpha = 0.3f) else Green.copy(alpha = 0.8f)

val BrightGreen = Color.Green
val Green = Color(0xFF5ccf92)
val Pink = Color(0xFFFF577D)
val Red = Color(0xffd30d09)

val primaryWhite = Color(0xFFFFFFFF)
val secondaryWhite = Color(0xFFEAE8EF)
val tertiaryWhite = Color(0xFFFAFAFA)

val primaryBlack = Color(0xFF000000)
val secondaryBlack = Color(0xFF302F2F)
val tertiaryBlack = Color(0xFF464646)