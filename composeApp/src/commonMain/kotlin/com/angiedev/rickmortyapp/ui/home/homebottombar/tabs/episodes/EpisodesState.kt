package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class EpisodesState(
    val episodes: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playerVideoUrl: String = "",
)
