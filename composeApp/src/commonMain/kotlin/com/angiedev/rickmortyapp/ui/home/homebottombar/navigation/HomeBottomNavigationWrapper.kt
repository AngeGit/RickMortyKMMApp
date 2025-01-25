package com.angiedev.rickmortyapp.ui.home.homebottombar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angiedev.rickmortyapp.ui.core.navigation.Routes
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters.CharactersScreen
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes.EpisodesScreen

@Composable
fun HomeBottomBarNavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(Routes.Characters.route) {
            CharactersScreen()
        }
    }
}