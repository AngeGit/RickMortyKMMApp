package com.angiedev.rickmortyapp.domain

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

class GetAllEpisodesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<PagingData<EpisodeModel>> = repository.getAllEpisodes()
}