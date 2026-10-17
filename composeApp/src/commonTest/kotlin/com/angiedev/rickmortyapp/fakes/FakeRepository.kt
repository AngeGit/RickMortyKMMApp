package com.angiedev.rickmortyapp.fakes

import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.Repository
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.fixtures.aCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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

    // Paginated lists
    var charactersPagingData: PagingData<CharacterModel> = PagingData.empty()
    var episodesPagingData: PagingData<EpisodeModel> = PagingData.empty()

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

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> = flowOf(charactersPagingData)

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> = flowOf(episodesPagingData)

    override suspend fun getEpisodes(episodeList: List<String>): List<EpisodeModel> {
        requestedEpisodeLists += episodeList
        getEpisodesError?.let { throw it }
        return episodes
    }
}
