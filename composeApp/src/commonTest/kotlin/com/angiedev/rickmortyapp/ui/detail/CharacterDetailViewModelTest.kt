package com.angiedev.rickmortyapp.ui.detail

import app.cash.turbine.test
import com.angiedev.rickmortyapp.domain.GetEpisodesUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fixtures.aCharacter
import com.angiedev.rickmortyapp.fixtures.anEpisode
import com.angiedev.rickmortyapp.testing.MainDispatcherTest
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CharacterDetailViewModelTest : MainDispatcherTest() {

    private val repository = FakeRepository()
    private val character = aCharacter(episodes = listOf("1", "2"))

    private fun createViewModel() = CharacterDetailViewModel(
        characterModel = character,
        getEpisodes = GetEpisodesUseCase(repository),
    )

    @Test
    fun `the first state has the character`() {
        assertEquals(character, createViewModel().uiState.value.characterModel)
    }

    @Test
    fun `the first state has no episodes yet`() {
        assertNull(createViewModel().uiState.value.episodeList)
    }

    @Test
    fun `after loading the state holds the character's episodes`() = runTest {
        repository.episodes = listOf(anEpisode(id = 1), anEpisode(id = 2, name = "Lawnmower Dog"))

        createViewModel().uiState.test {
            skipItems(1) // initial state

            assertEquals(repository.episodes, awaitItem().episodeList)
        }
    }

    @Test
    fun `asks for the episodes the character appears in`() = runTest {
        createViewModel()
        advanceUntilIdle()

        assertEquals(listOf(listOf("1", "2")), repository.requestedEpisodeLists)
    }
}
