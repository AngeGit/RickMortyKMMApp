package com.angiedev.rickmortyapp.domain

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacter(id: Int): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterOfTheDayDB()
}