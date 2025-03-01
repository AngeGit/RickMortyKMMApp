package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.domain.model.EpisodeModel

@Composable
fun CharacterEpisodeItem(episode: EpisodeModel) =
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
    ) {
        Text(
            text = episode.episode.trim(),
            modifier = Modifier.wrapContentWidth().padding(end = 8.dp)
        )
        Text(
            text = episode.name
        )
    }




