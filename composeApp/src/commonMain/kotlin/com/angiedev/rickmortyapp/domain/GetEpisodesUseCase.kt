package com.angiedev.rickmortyapp.domain

class GetEpisodesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(episodeList: List<String>) = repository.getEpisodes(episodeList)
}