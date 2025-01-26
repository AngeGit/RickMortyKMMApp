package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharacterState(
    val characterOfTheDay: CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)