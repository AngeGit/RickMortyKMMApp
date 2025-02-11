package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import com.angiedev.rickmortyapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodesViewModel(
    val getAllEpisodesUseCase: GetAllEpisodesUseCase,
    private val repository: Repository
): ViewModel() {

    private val _state = MutableStateFlow<EpisodesState>(EpisodesState())
    val state: StateFlow<EpisodesState> = _state

    init {
        _state.update { state ->
            state.copy(
                episodes = repository.getAllEpisodes().cachedIn(viewModelScope)
            )
        }

/*
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getAllEpisodesUseCase()
            }
            _state.update { it.copy(episodes = result) }
        }*/
    }

}