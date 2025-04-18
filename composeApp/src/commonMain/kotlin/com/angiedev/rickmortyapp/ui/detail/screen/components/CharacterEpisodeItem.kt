package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.ui.core.DefaultTextColor

@Composable
fun CharacterEpisodeItem(episode: EpisodeModel) =
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
    ) {
        Text(
            text = episode.episode,
            modifier = Modifier.wrapContentWidth(),
            color = DefaultTextColor,
            fontWeight = FontWeight.Bold
        )
        CustomHorizontalSpacer()
        Text(
            text = episode.name,
            color = DefaultTextColor,
            fontStyle = FontStyle.Italic
        )
    }




