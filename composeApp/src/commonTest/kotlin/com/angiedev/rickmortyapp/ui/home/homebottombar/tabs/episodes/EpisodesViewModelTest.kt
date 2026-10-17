package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import com.angiedev.rickmortyapp.fakes.FakeRepository
import com.angiedev.rickmortyapp.fixtures.anEpisode
import com.angiedev.rickmortyapp.testing.MainDispatcherTest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EpisodesViewModelTest : MainDispatcherTest() {

    private val repository = FakeRepository()

    private fun createViewModel() = EpisodesViewModel(
        getAllEpisodesUseCase = GetAllEpisodesUseCase(repository),
    )

    @Test
    fun `the first state has no video selected`() {
        assertEquals("", createViewModel().state.value.playerVideoUrl)
    }

    @Test
    fun `selecting an episode plays its video`() = runTest {
        val viewModel = createViewModel()

        viewModel.state.test {
            skipItems(1) // initial state

            viewModel.onEpisodeSelected(anEpisode(videoUrl = "6Hb7VdgW1nc"))

            assertEquals("6Hb7VdgW1nc", awaitItem().playerVideoUrl)
        }
    }

    @Test
    fun `closing the player clears the video`() = runTest {
        val viewModel = createViewModel()
        viewModel.onEpisodeSelected(anEpisode(videoUrl = "6Hb7VdgW1nc"))

        viewModel.state.test {
            skipItems(1) // the selected video

            viewModel.onVideoPlayerClose()

            assertEquals("", awaitItem().playerVideoUrl)
        }
    }

    @Test
    fun `episodes exposes the repository's paged list`() = runTest {
        repository.pagedEpisodes = listOf(anEpisode(id = 1), anEpisode(id = 2))

        val episodes = createViewModel().episodes.asSnapshot()

        assertEquals(listOf(1, 2), episodes.map { it.id })
    }
}
