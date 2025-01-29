package com.angiedev.rickmortyapp.domain

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterOfTheDay(characterOfTheDay: CharacterOfTheDayModel)
    suspend fun getCharacter(id: Int): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}