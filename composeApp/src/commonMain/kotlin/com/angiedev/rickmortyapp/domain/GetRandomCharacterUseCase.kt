package com.angiedev.rickmortyapp.domain

import com.angiedev.rickmortyapp.domain.model.CharacterModel

class GetRandomCharacterUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): CharacterModel {
        val randomId: Int = (1..826).random()
        return repository.getCharacter(randomId)
    }
}