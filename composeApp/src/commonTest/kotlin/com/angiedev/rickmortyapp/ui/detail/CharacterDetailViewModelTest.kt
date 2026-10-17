package com.angiedev.rickmortyapp.ui.detail

import app.cash.turbine.test
import com.angiedev.rickmortyapp.domain.GetEpisodesUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fixtures.aCharacter
import com.angiedev.rickmortyapp.fixtures.anEpisode
import com.angiedev.rickmortyapp.testing.MainDispatcherTest
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

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

    //region Errors
    @Test
    fun `failing episodes do not crash the screen`() = runTest {
        repository.getEpisodesError = IllegalStateException("No network")

        createViewModel()
        advanceUntilIdle()
    }

    @Test
    fun `failing episodes show an error`() = runTest {
        repository.getEpisodesError = IllegalStateException("No network")

        createViewModel().uiState.test {
            skipItems(1) // initial state

            assertTrue(awaitItem().isEpisodesError)
        }
    }

    @Test
    fun `a cancelled episodes load is not reported as an error`() = runTest {
        repository.getEpisodesError = CancellationException("Cancelled")

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isEpisodesError)
    }
    //endregion Errors
}
