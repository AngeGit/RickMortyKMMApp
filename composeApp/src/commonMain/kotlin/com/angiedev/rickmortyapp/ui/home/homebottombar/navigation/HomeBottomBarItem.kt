package com.angiedev.rickmortyapp.ui.home.homebottombar.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.ui.core.navigation.Routes
import com.angiedev.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem
import org.jetbrains.compose.resources.painterResource
import rickmortykmmapp.composeapp.generated.resources.Res
import rickmortykmmapp.composeapp.generated.resources.ic_characters
import rickmortykmmapp.composeapp.generated.resources.ic_player

sealed class HomeBottomBarItem : BottomBarItem {
    abstract override val route: String
    abstract override val title: String
    abstract override val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_player),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
        }
    ) : HomeBottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_characters),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
        }
    ) : HomeBottomBarItem()
}