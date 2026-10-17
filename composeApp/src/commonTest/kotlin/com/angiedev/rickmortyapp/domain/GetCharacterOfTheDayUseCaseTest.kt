package com.angiedev.rickmortyapp.domain

import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fakes.TestClock
import com.angiedev.rickmortyapp.fixtures.aCharacter
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.time.Duration.Companion.days

class GetCharacterOfTheDayUseCaseTest {

    private val repository = FakeRepository()

    // No DST change around mid-January, so adding one day always lands on the next local date
    private val clock = TestClock(Instant.parse("2025-01-15T12:00:00Z"))

    private val useCase = GetCharacterOfTheDayUseCase(repository, clock, Random(SEED))

    @Test
    fun `with nothing saved fetches a character and saves it`() = runTest {
        val character = useCase()

        assertEquals(listOf(character.id), repository.requestedCharacterIds)
        assertEquals(character, repository.savedCharacterOfTheDay?.character)
    }

    @Test
    fun `picks the character id with the injected random`() = runTest {
        useCase()

        assertEquals(listOf((1..826).random(Random(SEED))), repository.requestedCharacterIds)
    }

    @Test
    fun `on the same day returns the saved character without fetching again`() = runTest {
        val first = useCase()

        val second = useCase()

        assertEquals(first, second)
        assertEquals(1, repository.requestedCharacterIds.size)
    }

    @Test
    fun `on a new day fetches and saves a new character`() = runTest {
        useCase()
        val firstDate = repository.savedCharacterOfTheDay?.selectedDate

        clock.current += 1.days
        val second = useCase()

        assertEquals(2, repository.requestedCharacterIds.size)
        assertEquals(second, repository.savedCharacterOfTheDay?.character)
        assertNotEquals(firstDate, repository.savedCharacterOfTheDay?.selectedDate)
    }

    @Test
    fun `a character saved on another day is replaced`() = runTest {
        repository.savedCharacterOfTheDay = CharacterOfTheDayModel(
            character = aCharacter(id = OLD_CHARACTER_ID),
            selectedDate = "12024",
        )

        val character = useCase()

        assertNotEquals(OLD_CHARACTER_ID, character.id)
        assertEquals(character, repository.savedCharacterOfTheDay?.character)
    }

    @Test
    fun `when fetching fails the error reaches the caller and nothing is saved`() = runTest {
        repository.getCharacterError = IllegalStateException("No network")

        assertFailsWith<IllegalStateException> { useCase() }
        assertNull(repository.savedCharacterOfTheDay)
    }

    private companion object {
        const val SEED = 42

        // Outside 1..826, so the use case can never pick it by chance
        const val OLD_CHARACTER_ID = 999
    }
}
