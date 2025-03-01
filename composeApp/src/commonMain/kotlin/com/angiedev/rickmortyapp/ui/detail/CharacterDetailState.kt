package com.angiedev.rickmortyapp.ui.detail

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.EpisodeModel

data class CharacterDetailState(
    val characterModel: CharacterModel,
    val episodeList: List<EpisodeModel>? = null
)
