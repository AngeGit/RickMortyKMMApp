package com.angiedev.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.detail.CharacterDetailScreen
import com.angiedev.rickmortyapp.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper() {
    val mainAppController = rememberNavController()
    NavHost(navController = mainAppController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(mainAppController)
        }

        composable<CharacterDetail> { navBackStackEntry ->
            val characterDetailEncoding = navBackStackEntry.toRoute<CharacterDetail>()
            val characterModel: CharacterModel =
                Json.decodeFromString<CharacterModel>(characterDetailEncoding.characterModelEncode)
            CharacterDetailScreen(characterModel)
        }
    }
}