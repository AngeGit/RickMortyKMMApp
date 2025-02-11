package com.angiedev.rickmortyapp.ui.core.components.videoplayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(
    modifier: Modifier,
    videoUrl: String,
)