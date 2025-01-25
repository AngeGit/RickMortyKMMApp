package com.angiedev.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angiedev.rickmortyapp.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainAppController = rememberNavController()
    NavHost(navController = mainAppController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}