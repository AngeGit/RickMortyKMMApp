package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodesViewModel(
    val getAllEpisodesUseCase: GetAllEpisodesUseCase
): ViewModel() {

    private val _state = MutableStateFlow<EpisodesState>(EpisodesState())
    val state: StateFlow<EpisodesState> = _state

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getAllEpisodesUseCase()
            }
            _state.update { it.copy(episodes = result) }
        }
    }

}