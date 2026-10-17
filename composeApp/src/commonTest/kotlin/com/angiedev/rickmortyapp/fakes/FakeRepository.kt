package com.angiedev.rickmortyapp.fakes

import androidx.paging.testing.asPagingSourceFactory
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.Repository
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.fixtures.aCharacter
import kotlinx.coroutines.flow.Flow

/**
 * In-memory [Repository]. Each method can return a canned value or throw a configured error,
 * and records the arguments it was called with.
 */
class FakeRepository : Repository {

    // Character of the day: kept in memory instead of Room
    var savedCharacterOfTheDay: CharacterOfTheDayModel? = null

    // Single character
    var characterById: (Int) -> CharacterModel = { id -> aCharacter(id = id) }
    var getCharacterError: Throwable? = null
    val requestedCharacterIds = mutableListOf<Int>()

    // Paginated lists, served through a real Pager like RepositoryImpl does.
    // Set them before creating the ViewModel: the flow is built when it is requested.
    var pagedCharacters: List<CharacterModel> = emptyList()
    var pagedEpisodes: List<EpisodeModel> = emptyList()

    // Episodes of a character
    var episodes: List<EpisodeModel> = emptyList()
    var getEpisodesError: Throwable? = null
    val requestedEpisodeLists = mutableListOf<List<String>>()

    override suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayModel? = savedCharacterOfTheDay

    override suspend fun saveCharacterOfTheDay(characterOfTheDay: CharacterOfTheDayModel) {
        savedCharacterOfTheDay = characterOfTheDay
    }

    override suspend fun getCharacter(id: Int): CharacterModel {
        requestedCharacterIds += id
        getCharacterError?.let { throw it }
        return characterById(id)
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE), pagingSourceFactory = pagedCharacters.asPagingSourceFactory()).flow

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE), pagingSourceFactory = pagedEpisodes.asPagingSourceFactory()).flow

    override suspend fun getEpisodes(episodeList: List<String>): List<EpisodeModel> {
        requestedEpisodeLists += episodeList
        getEpisodesError?.let { throw it }
        return episodes
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}
