package com.angiedev.rickmortyapp.domain

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> = repository.getAllCharacters()
}