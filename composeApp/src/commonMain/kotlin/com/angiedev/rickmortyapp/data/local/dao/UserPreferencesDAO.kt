package com.angiedev.rickmortyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.angiedev.rickmortyapp.data.local.entities.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM character_of_the_day")
    suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayEntity?
}