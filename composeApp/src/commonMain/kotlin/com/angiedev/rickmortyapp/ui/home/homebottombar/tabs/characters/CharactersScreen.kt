package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.angiedev.rickmortyapp.ui.core.navigation.Routes

@Composable
fun CharactersScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red)) {
        Text(text = "Characters Screen")
    }
}
