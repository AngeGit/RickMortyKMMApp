package com.angiedev.rickmortyapp.data

import com.angiedev.rickmortyapp.data.remote.ApiService
import com.angiedev.rickmortyapp.domain.Repository
import com.angiedev.rickmortyapp.domain.model.CharacterModel

class RepositoryImpl(
    private val api: ApiService
) : Repository {
    override suspend fun getCharacter(id: Int): CharacterModel =
        api.getCharacterById(id).toDomain()
}