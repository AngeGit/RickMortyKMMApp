package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EpisodesViewModel(
    val getAllEpisodesUseCase: GetAllEpisodesUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(EpisodesState())
    val state: StateFlow<EpisodesState> = _state

    init {
        _state.update { state ->
            state.copy(
                episodes = getAllEpisodesUseCase().cachedIn(viewModelScope)
            )
        }
    }

    fun onEpisodeSelected(selectedEpisode: EpisodeModel){
        _state.update { it.copy(playerVideoUrl = selectedEpisode.videoUrl) }
    }
    fun onVideoPlayerClose(){
        _state.update { it.copy(playerVideoUrl = "") }
    }

}