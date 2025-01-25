package com.angiedev.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.angiedev.rickmortyapp.ui.home.homebottombar.navigation.HomeBottomBarItem


@Composable
fun BottomBarNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                onClick = {
                    navController.navigate(item.route){
                        //retrieves the start destination route of the navigation graph and assigns it to the variable route if it is not null.
                        navController.graph.startDestinationRoute?.let { route->
                           //This navigates back to the start destination and saves the state of the navigation
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        //This ensures that if the destination is already at the top of the back stack, a new instance of the destination is not created.
                        launchSingleTop = true
                        //This restores the state of the destination if it is already in the back stack.
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = { Text(item.title) }
            )
        }
    }
}