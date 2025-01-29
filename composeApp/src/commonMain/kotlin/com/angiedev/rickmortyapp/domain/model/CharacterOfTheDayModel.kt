package com.angiedev.rickmortyapp.domain.model

import com.angiedev.rickmortyapp.data.local.entities.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(
    val character: CharacterModel,
    val selectedDate: String
){
    fun toEntity() = CharacterOfTheDayEntity(
        id = character.id,
        status = character.isAlive,
        image = character.image,
        name = character.name,
        selectedDate = selectedDate
    )
}