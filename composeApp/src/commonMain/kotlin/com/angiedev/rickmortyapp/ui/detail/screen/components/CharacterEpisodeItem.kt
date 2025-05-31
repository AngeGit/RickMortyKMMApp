package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.ui.core.resources.DefaultTextColor

@Composable
fun CharacterEpisodeItem(episode: EpisodeModel) =
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(DETAIL_ARRANGEMENT_SPACING)
    ) {
        Text(
            text = episode.episode,
            color = DefaultTextColor,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = episode.name,
            color = DefaultTextColor,
            fontStyle = FontStyle.Italic
        )
    }