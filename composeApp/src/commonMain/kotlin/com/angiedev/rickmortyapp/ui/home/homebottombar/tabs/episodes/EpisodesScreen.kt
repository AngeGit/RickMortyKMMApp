package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun EpisodesScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Blue)) {
        Text(text = "Episodes Screen")
    }
}
