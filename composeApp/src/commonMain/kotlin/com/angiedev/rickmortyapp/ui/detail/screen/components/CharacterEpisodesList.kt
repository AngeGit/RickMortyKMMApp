package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import com.angiedev.rickmortyapp.ui.core.components.ErrorView
import androidx.compose.ui.Modifier
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.ui.core.components.Loading


@Composable
fun CharacterEpisodesList(
    episodeList: List<EpisodeModel>?,
    isError: Boolean,
    onRetry: () -> Unit,
    modifier: Modifier,
) =
    CharacterCard(modifier) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            CharacterDetailTitle("EPISODE'S APPEARANCES")
            CustomVerticalSpacer()
            when {
                episodeList != null -> episodeList.forEach { episode ->
                    CharacterEpisodeItem(episode)
                }
                isError -> ErrorView(onRetry = onRetry)
                else -> Loading()
            }
        }
    }


