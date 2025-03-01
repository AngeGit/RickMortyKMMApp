package com.angiedev.rickmortyapp.ui.core.navigation

import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {
    data object Home : Routes("home")

    //HomeBottomNav
    data object Characters : Routes("characters")
    data object Episodes : Routes("episodes")
}

@Serializable
data class CharacterDetail(val characterModelEncode: String)