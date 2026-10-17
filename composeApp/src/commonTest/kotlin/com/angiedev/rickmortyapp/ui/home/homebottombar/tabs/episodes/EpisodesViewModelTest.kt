package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.testing.MainDispatcherTest

class EpisodesViewModelTest : MainDispatcherTest() {

    private val repository = FakeRepository()

    private fun createViewModel() = EpisodesViewModel(
        getAllEpisodesUseCase = GetAllEpisodesUseCase(repository),
    )

    // Suggested cases. Turbine: createViewModel().state.test { ... }
    // - The first state has no video selected (playerVideoUrl is empty).
    // - onEpisodeSelected(anEpisode(videoUrl = "...")) sets playerVideoUrl to that video.
    // - onVideoPlayerClose() clears playerVideoUrl again.
    // - `episodes` exposes the repository's paged list (repository.pagedEpisodes, set before creating
    //   the ViewModel, + asSnapshot()).
}
