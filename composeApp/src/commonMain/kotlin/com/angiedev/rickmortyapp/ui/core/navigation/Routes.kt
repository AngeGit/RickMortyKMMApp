package com.angiedev.rickmortyapp.ui.core.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")

    //HomeBottomNav
    data object Characters : Routes("characters")
    data object Episodes : Routes("episodes")
}