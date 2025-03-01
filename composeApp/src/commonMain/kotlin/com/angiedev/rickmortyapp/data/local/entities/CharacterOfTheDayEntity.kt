package com.angiedev.rickmortyapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angiedev.rickmortyapp.data.remote.responses.characters.OriginResponse

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity (
    @PrimaryKey
    val id: Int,
    val status: Boolean,
    val image: String,
    val name:String,
    val selectedDate: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
){
    companion object {
        const val IS_ALIVE = "alive"
    }
    fun toDomain() = CharacterOfTheDayModel(
        character = CharacterModel(
            id = id,
            isAlive = status,
            image = image,
            name = name,
            species = species,
            gender = gender,
            origin = origin,
            episodes = Json.decodeFromString<List<String>>(episodes)
        ),
        selectedDate = selectedDate
    )
}