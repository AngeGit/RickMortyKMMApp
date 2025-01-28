package com.angiedev.rickmortyapp.domain

import androidx.compose.ui.input.key.Key.Companion.Calendar
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): CharacterModel {

        val randomId: Int = (1..826).random()
        return repository.getCharacter(randomId)
    }
    private fun getCurrentDayOfTheYear(): String {
       val instant: Instant = Clock.System.now()
       val localTime: LocalDateTime= instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}