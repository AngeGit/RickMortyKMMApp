package com.angiedev.rickmortyapp.ui.detail

import com.angiedev.rickmortyapp.domain.GetEpisodesUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fixtures.aCharacter
import com.angiedev.rickmortyapp.testing.MainDispatcherTest

class CharacterDetailViewModelTest : MainDispatcherTest() {

    private val repository = FakeRepository()
    private val character = aCharacter(episodes = listOf("1", "2"))

    private fun createViewModel() = CharacterDetailViewModel(
        characterModel = character,
        getEpisodes = GetEpisodesUseCase(repository),
    )

    // Suggested cases. Turbine: createViewModel().uiState.test { ... }
    // - The first state has the character and no episodes yet (episodeList == null).
    // - After init, episodeList holds what the repository returns (repository.episodes).
    // - It asks for the character's episode ids (repository.requestedEpisodeLists).
}
