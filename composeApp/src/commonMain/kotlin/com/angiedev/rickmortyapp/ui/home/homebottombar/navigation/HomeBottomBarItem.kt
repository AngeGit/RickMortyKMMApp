package com.angiedev.rickmortyapp.ui.home.homebottombar.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.angiedev.rickmortyapp.ui.core.navigation.Routes
import com.angiedev.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem

sealed class HomeBottomBarItem : BottomBarItem {
    abstract override val route: String
    abstract override val title: String
    abstract override val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, contentDescription = title)
        }
    ) : HomeBottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, contentDescription = title)
        }
    ) : HomeBottomBarItem()
}