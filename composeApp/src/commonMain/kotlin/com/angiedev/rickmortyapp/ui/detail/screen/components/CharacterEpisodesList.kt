package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.ui.core.components.Loading


@Composable
fun CharacterEpisodesList(episodeList: List<EpisodeModel>?, modifier: Modifier) =
    CharacterCard(modifier) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            episodeList?.let {
                it.forEach { episode ->
                    CharacterEpisodeItem(episode)
                }
            } ?: Loading()
        }
    }


