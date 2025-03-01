package com.angiedev.rickmortyapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angiedev.rickmortyapp.domain.GetEpisodesUseCase
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    characterModel: CharacterModel,
    val getEpisodes: GetEpisodesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDetailState(characterModel))
    val uiState: StateFlow<CharacterDetailState> = _uiState

    init {
        getEpisodesForCharacter(characterModel.episodes)
    }

    private fun getEpisodesForCharacter(episodes: List<String>) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getEpisodes(episodes)
            }
            _uiState.update { it.copy(episodeList = result) }
        }
    }
}