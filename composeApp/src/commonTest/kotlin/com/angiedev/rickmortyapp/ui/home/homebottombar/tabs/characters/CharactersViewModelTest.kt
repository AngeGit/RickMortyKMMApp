package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fakes.TestClock
import com.angiedev.rickmortyapp.testing.MainDispatcherTest
import kotlinx.datetime.Instant
import kotlin.random.Random

class CharactersViewModelTest : MainDispatcherTest() {

    private val repository = FakeRepository()
    private val clock = TestClock(Instant.parse("2025-01-15T12:00:00Z"))

    private fun createViewModel() = CharactersViewModel(
        getCharacterOfTheDayUseCase = GetCharacterOfTheDayUseCase(repository, clock, Random(42)),
        getAllCharacters = GetAllCharactersUseCase(repository),
    )

    // Suggested cases. Turbine: createViewModel().characterOfTheDayState.test { ... }
    // - The first state has no character of the day yet.
    // - After init, the state holds the character of the day the repository returns.
    // - If a character was saved today (repository.savedCharacterOfTheDay), it is shown and
    //   repository.requestedCharacterIds stays empty.
    // - `characters` (a ViewModel property, not part of the state) exposes the repository's paged list:
    //   set repository.pagedCharacters = listOf(aCharacter(id = 1), aCharacter(id = 2)) before creating
    //   the ViewModel; viewModel.characters.asSnapshot() (androidx.paging.testing) returns those items.
}
