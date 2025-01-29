package com.angiedev.rickmortyapp.data

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.data.local.RickMortyDatabase
import com.angiedev.rickmortyapp.data.remote.ApiService
import com.angiedev.rickmortyapp.data.remote.paging.CharactersPagingSource
import com.angiedev.rickmortyapp.domain.Repository
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val db: RickMortyDatabase,
) : Repository {

    companion object {
        // Number of items to load in each page, should be provided by the Rick and Morty API
        const val MAX_CHARACTERS = 20
        const val PREFETCH_ITEMS = 4
    }

    override suspend fun getCharacter(id: Int): CharacterModel =
        api.getCharacterById(id).toDomain()

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> =
        Pager(
            config = PagingConfig(
                pageSize = MAX_CHARACTERS,
                prefetchDistance = PREFETCH_ITEMS,
            )
        ) {
            charactersPagingSource
        }.flow

    override suspend fun getCharacterOfTheDayDB() {
        db.userPreferencesDao().getCharacterOfTheDayDB()
    }
}