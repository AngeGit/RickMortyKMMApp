package com.angiedev.rickmortyapp.ui.home.homebottombar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.angiedev.rickmortyapp.ui.core.navigation.CharacterDetail
import com.angiedev.rickmortyapp.ui.core.navigation.Routes
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters.CharactersScreen
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes.EpisodesScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun HomeBottomBarNavigationWrapper(
    mainNavController: NavController,
    bottomBarNavController: NavHostController
) {
    NavHost(navController = bottomBarNavController, startDestination = Routes.Episodes.route) {
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(Routes.Characters.route) {
            CharactersScreen(
                onNavigateDetail = { characterModel ->
                    val encode: String = Json.encodeToString(characterModel)
                    mainNavController.navigate(CharacterDetail(encode))
                }
            )
        }
    }
}