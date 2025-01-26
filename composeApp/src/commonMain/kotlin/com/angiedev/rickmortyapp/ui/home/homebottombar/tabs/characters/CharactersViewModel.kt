package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetRandomCharacterUseCase
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val getRandomCharacterUseCase: GetRandomCharacterUseCase,
    private val getAllCharacters: GetAllCharactersUseCase
): ViewModel(){

    private val _characterOfTheDayState = MutableStateFlow(CharacterState())
    val characterOfTheDayState: StateFlow<CharacterState> = _characterOfTheDayState

    init { //Not sure it correct to use init here. Maybe this should be in a function called on the onViewCreated()
        viewModelScope.launch {
            val result: CharacterModel = withContext(Dispatchers.IO){
                getRandomCharacterUseCase()
            }
            _characterOfTheDayState.update { it.copy(characterOfTheDay = result) }
        }
        fetchAllCharacters()
    }
    private fun fetchAllCharacters() {
        _characterOfTheDayState.update { it.copy(characters = getAllCharacters()) }
    }

}