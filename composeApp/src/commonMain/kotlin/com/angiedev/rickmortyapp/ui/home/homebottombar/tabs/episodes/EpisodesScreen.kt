package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.PagingGridWrapper
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.PagingType
import com.angiedev.rickmortyapp.ui.core.components.videoplayer.VideoPlayerView
import com.angiedev.rickmortyapp.ui.core.resources.BackgroundPrimaryColor
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {
    val episodesViewModel = koinViewModel<EpisodesViewModel>()
    val episodesState = episodesViewModel.state.collectAsStateWithLifecycle()
    val episodes = episodesState.value.episodes.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimaryColor)
            .padding(16.dp)
    ) {
        val scrollProgress = remember { mutableFloatStateOf(0.01f) }

        PagingGridWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            itemView = {
                EpisodeItemList(it) { selectedEpisode ->
                    episodesViewModel.onEpisodeSelected(
                        selectedEpisode
                    )
                }
            },
            progress = { progress ->
             scrollProgress.value = progress + 0.01f
            }
        )

        EpisodesProgressBar(scrollProgress.value)

        VideoPlayerView(
            episodesState.value.playerVideoUrl,
        ) { episodesViewModel.onVideoPlayerClose() }
    }
}


