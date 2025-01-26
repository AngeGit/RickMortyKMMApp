package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import com.angiedev.rickmortyapp.domain.model.CharacterModel

data class CharacterState(
    val characterOfTheDay: CharacterModel? = null,
)