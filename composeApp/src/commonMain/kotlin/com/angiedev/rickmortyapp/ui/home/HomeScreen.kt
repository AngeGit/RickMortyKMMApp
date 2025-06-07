package com.angiedev.rickmortyapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.angiedev.rickmortyapp.ui.home.homebottombar.navigation.HomeBottomBarItem
import com.angiedev.rickmortyapp.ui.home.homebottombar.navigation.HomeBottomBarNavigationWrapper
import com.angiedev.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarNavigation
import com.angiedev.rickmortyapp.ui.core.resources.BackgroundPrimaryColor
import com.angiedev.rickmortyapp.ui.core.resources.Strings
import org.jetbrains.compose.resources.painterResource
import rickmortykmmapp.composeapp.generated.resources.Res
import rickmortykmmapp.composeapp.generated.resources.rick
import rickmortykmmapp.composeapp.generated.resources.ricktoolbar

@Composable
fun HomeScreen(
    mainNavController: NavController
) {
    val homeBottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBarNavigation(getBottomBarItems(), homeBottomBarNavController) },
        topBar = {
            Column {
                HomeTopAppBar()
                RedTopAppBar()
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            HomeBottomBarNavigationWrapper(mainNavController, homeBottomBarNavController)
        }
    }
}

private fun getBottomBarItems(): List<HomeBottomBarItem> = listOf(HomeBottomBarItem.Episodes(), HomeBottomBarItem.Characters())

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() =
    TopAppBar(
        modifier = Modifier.background(BackgroundPrimaryColor).padding(top = 8.dp),
        title = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundPrimaryColor)
                    ,
                painter = painterResource(Res.drawable.ricktoolbar),
                contentDescription = Strings.CD_TOPAPPBAR,
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomCenter
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = BackgroundPrimaryColor),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedTopAppBar() =
    TopAppBar(
        modifier = Modifier.background(com.angiedev.rickmortyapp.ui.core.resources.Red),
        title = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .background(com.angiedev.rickmortyapp.ui.core.resources.Red),
                painter = painterResource(Res.drawable.ricktoolbar),
                contentDescription = Strings.CD_TOPAPPBAR,
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomCenter
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = com.angiedev.rickmortyapp.ui.core.resources.Red),
    )
