package com.angiedev.rickmortyapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.angiedev.rickmortyapp.ui.home.homebottombar.navigation.HomeBottomBarItem
import com.angiedev.rickmortyapp.ui.home.homebottombar.navigation.HomeBottomBarNavigationWrapper
import com.angiedev.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarNavigation

@Composable
fun HomeScreen() {
    val homeBottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBarNavigation(getBottomBarItems(), homeBottomBarNavController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            HomeBottomBarNavigationWrapper(homeBottomBarNavController)
        }
    }
}

private fun getBottomBarItems(): List<HomeBottomBarItem> = listOf(HomeBottomBarItem.Episodes(), HomeBottomBarItem.Characters())

