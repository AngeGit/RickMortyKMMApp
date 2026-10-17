package com.angiedev.rickmortyapp.fakes

import com.angiedev.rickmortyapp.data.local.dao.UserPreferencesDAO
import com.angiedev.rickmortyapp.data.local.entities.CharacterOfTheDayEntity

/** In-memory [UserPreferencesDAO]: keeps the last character of the day inserted. */
class FakeUserPreferencesDao : UserPreferencesDAO {

    var stored: CharacterOfTheDayEntity? = null

    override suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayEntity? = stored

    override suspend fun insertCharacterOfTheDay(characterOfTheDayEntity: CharacterOfTheDayEntity) {
        stored = characterOfTheDayEntity
    }
}
