package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.ui.core.resources.BackgroundSecondaryColor
import com.angiedev.rickmortyapp.ui.core.resources.HighlightBorderColor


@Composable
fun EpisodesProgressBar(scrollProgress: Float) {
    val animatedProgress by animateFloatAsState(targetValue = scrollProgress)

    Spacer(modifier = Modifier.height(16.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = { animatedProgress },
        color = HighlightBorderColor,
        trackColor = BackgroundSecondaryColor,
        gapSize = 8.dp,
        drawStopIndicator = {},
    )
}
