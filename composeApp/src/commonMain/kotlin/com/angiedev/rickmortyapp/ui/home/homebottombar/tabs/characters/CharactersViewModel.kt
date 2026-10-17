package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import app.cash.paging.PagingData
import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharacterOfTheDayUseCase: GetCharacterOfTheDayUseCase,
    private val getAllCharacters: GetAllCharactersUseCase
): ViewModel(){

    private val _characterOfTheDayState = MutableStateFlow(CharacterState())
    val characterOfTheDayState: StateFlow<CharacterState> = _characterOfTheDayState

    // Exposed apart from CharacterState: a Flow inside a data class breaks equals() and Compose stability.
    // cachedIn keeps the loaded pages across configuration changes and tab switches.
    val characters: Flow<PagingData<CharacterModel>> = getAllCharacters().cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            try {
                val result: CharacterModel = getCharacterOfTheDayUseCase()
                _characterOfTheDayState.update { it.copy(characterOfTheDay = result) }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _characterOfTheDayState.update { it.copy(isCharacterOfTheDayError = true) }
            }
        }
    }
}