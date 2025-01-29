package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val getCharacterOfTheDayUseCase: GetCharacterOfTheDayUseCase,
    private val getAllCharacters: GetAllCharactersUseCase
): ViewModel(){

    private val _characterOfTheDayState = MutableStateFlow(CharacterState())
    val characterOfTheDayState: StateFlow<CharacterState> = _characterOfTheDayState

    init {
        viewModelScope.launch {
            val result: CharacterModel = withContext(Dispatchers.IO){
                getCharacterOfTheDayUseCase()
            }
            _characterOfTheDayState.update { it.copy(characterOfTheDay = result) }
        }
        fetchAllCharacters()
    }

    private fun fetchAllCharacters() =
        //cachedIn(viewModelScope) is't a recomendation of Google because Pagging3 library lose the state for a while
        // and we lose the position of the list. With that we don't lose the state of the list
        _characterOfTheDayState.update {
            it.copy(
                characters = getAllCharacters().cachedIn(viewModelScope)
            )
        }
}