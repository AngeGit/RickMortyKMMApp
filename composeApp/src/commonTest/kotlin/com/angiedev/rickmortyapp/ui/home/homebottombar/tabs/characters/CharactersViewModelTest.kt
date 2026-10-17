package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fakes.TestClock
import com.angiedev.rickmortyapp.fixtures.aCharacter
import com.angiedev.rickmortyapp.testing.MainDispatcherTest
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CharactersViewModelTest : MainDispatcherTest() {

    private val repository = FakeRepository()
    private val clock = TestClock(Instant.parse("2025-01-15T12:00:00Z"))

    private fun createViewModel() = CharactersViewModel(
        getCharacterOfTheDayUseCase = GetCharacterOfTheDayUseCase(repository, clock, Random(42)),
        getAllCharacters = GetAllCharactersUseCase(repository),
    )

    @Test
    fun `the first state has no character of the day yet`() {
        assertNull(createViewModel().characterOfTheDayState.value.characterOfTheDay)
    }

    @Test
    fun `after loading the state holds the character of the day`() = runTest {
        repository.characterById = { id -> aCharacter(id = id, name = "Mr. Poopybutthole") }

        createViewModel().characterOfTheDayState.test {
            skipItems(1) // initial state

            val character = awaitItem().characterOfTheDay
            assertEquals(aCharacter(id = repository.requestedCharacterIds.single(), name = "Mr. Poopybutthole"), character)
        }
    }

    @Test
    fun `a character already picked today is shown without fetching a new one`() = runTest {
        // Picks and saves today's character, as an earlier app launch would have done
        val pickedToday = GetCharacterOfTheDayUseCase(repository, clock, Random(1))()

        createViewModel().characterOfTheDayState.test {
            skipItems(1) // initial state

            assertEquals(pickedToday, awaitItem().characterOfTheDay)
            assertEquals(1, repository.requestedCharacterIds.size)
        }
    }

    @Test
    fun `characters exposes the repository's paged list`() = runTest {
        repository.pagedCharacters = listOf(aCharacter(id = 1), aCharacter(id = 2))

        val characters = createViewModel().characters.asSnapshot()

        assertEquals(listOf(1, 2), characters.map { it.id })
    }

    //region Errors
    @Test
    fun `a failing character of the day does not crash the screen`() = runTest {
        repository.getCharacterError = IllegalStateException("No network")

        createViewModel()
        advanceUntilIdle()
    }

    @Test
    fun `a failing character of the day shows an error`() = runTest {
        repository.getCharacterError = IllegalStateException("No network")

        createViewModel().characterOfTheDayState.test {
            skipItems(1) // initial state

            assertTrue(awaitItem().isCharacterOfTheDayError)
        }
    }

    @Test
    fun `a cancelled load is not reported as an error`() = runTest {
        repository.getCharacterError = CancellationException("Cancelled")

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertFalse(viewModel.characterOfTheDayState.value.isCharacterOfTheDayError)
    }

    @Test
    fun `retrying after a failure loads the character of the day`() = runTest {
        repository.getCharacterError = IllegalStateException("No network")
        val viewModel = createViewModel()
        advanceUntilIdle()

        repository.getCharacterError = null
        viewModel.onCharacterOfTheDayRetryClick()
        advanceUntilIdle()

        assertNotNull(viewModel.characterOfTheDayState.value.characterOfTheDay)
    }

    @Test
    fun `a successful retry clears the error`() = runTest {
        repository.getCharacterError = IllegalStateException("No network")
        val viewModel = createViewModel()
        advanceUntilIdle()

        repository.getCharacterError = null
        viewModel.onCharacterOfTheDayRetryClick()
        advanceUntilIdle()

        assertFalse(viewModel.characterOfTheDayState.value.isCharacterOfTheDayError)
    }
    //endregion Errors
}
