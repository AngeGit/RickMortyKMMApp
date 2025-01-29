package com.angiedev.rickmortyapp.domain

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetCharacterOfTheDayUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): CharacterModel {
        val selectedDay = getCurrentDayOfTheYear()
        return repository.getCharacterOfTheDayDB()?.let { characterModel ->
            characterModel.character.takeIf { characterModel.selectedDate == selectedDay }
                ?: generateAndSaveRandomCharacter(selectedDay)
        } ?: generateAndSaveRandomCharacter(selectedDay)
    }

    private fun getCurrentDayOfTheYear(): String {
        val localTime: LocalDateTime =
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }

    private suspend fun generateAndSaveRandomCharacter(selectedDay: String): CharacterModel {
        return getRandomCharacter().also {
            saveCharacterOfTheDay(it, selectedDay)
        }
    }

    private suspend fun getRandomCharacter() = repository.getCharacter((1..826).random())

    private suspend fun saveCharacterOfTheDay(
        characterModel: CharacterModel,
        selectedDay: String
    ) = repository.saveCharacterOfTheDay(
        CharacterOfTheDayModel(
            character = characterModel,
            selectedDate = selectedDay
        )
    )
}