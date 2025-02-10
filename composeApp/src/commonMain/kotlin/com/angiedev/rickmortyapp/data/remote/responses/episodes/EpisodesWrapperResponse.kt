package com.angiedev.rickmortyapp.data.remote.responses.episodes

import com.angiedev.rickmortyapp.data.remote.responses.InfoResponse
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesWrapperResponse(
    val info: InfoResponse,
    val results: List<EpisodeResponse>
)