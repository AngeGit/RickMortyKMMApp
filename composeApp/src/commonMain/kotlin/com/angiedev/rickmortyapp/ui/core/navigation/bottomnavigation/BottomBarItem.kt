package com.angiedev.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable

interface BottomBarItem {
    val route: String
    val title: String
    val icon: @Composable () -> Unit
}