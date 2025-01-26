package com.angiedev.rickmortyapp.domain

import com.angiedev.rickmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getCharacter(id: Int): CharacterModel
}