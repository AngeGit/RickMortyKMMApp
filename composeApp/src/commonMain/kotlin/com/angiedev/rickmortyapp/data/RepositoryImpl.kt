package com.angiedev.rickmortyapp.data

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.data.local.dao.UserPreferencesDAO
import com.angiedev.rickmortyapp.data.local.entities.toEntity
import com.angiedev.rickmortyapp.data.remote.ApiService
import com.angiedev.rickmortyapp.data.remote.paging.CharactersPagingSource
import com.angiedev.rickmortyapp.data.remote.paging.EpisodesPagingSource
import com.angiedev.rickmortyapp.domain.Repository
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val userPreferencesDao: UserPreferencesDAO,
) : Repository {

    companion object {
        // Number of items to load in each page, should be provided by the Rick and Morty API
        const val MAX_CHARACTERS = 20
        const val PREFETCH_ITEMS = 4
    }

    //region Characters
    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> =
        Pager(
            config = PagingConfig(
                pageSize = MAX_CHARACTERS,
                prefetchDistance = PREFETCH_ITEMS,
            )
        ) {
            // Paging needs a new PagingSource every time the data is invalidated
            CharactersPagingSource(api)
        }.flow

    override suspend fun getCharacter(id: Int): CharacterModel =
        api.getCharacterById(id).toDomain()

    override suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayModel? =
        userPreferencesDao.getCharacterOfTheDayDB()?.toDomain()

    override suspend fun saveCharacterOfTheDay(characterOfTheDay: CharacterOfTheDayModel) {
        userPreferencesDao.insertCharacterOfTheDay(characterOfTheDay.toEntity())
    }
    //endregion Characters
    //region Episodes
    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> =
        Pager(
            config = PagingConfig(
                pageSize = MAX_CHARACTERS,
                prefetchDistance = PREFETCH_ITEMS,
            )
        ) { EpisodesPagingSource(api) }.flow

    override suspend fun getEpisodes(episodeList: List<String>): List<EpisodeModel> =
        when (episodeList.size) {
            0 -> emptyList()
            1 -> listOf(api.getSingleEpisode(episodeList.first()).toDomain())
            else -> api.getEpisodes(episodeList.joinToString(",")).map {
                it.toDomain()
            }
        }
    //endregion Episodes

}