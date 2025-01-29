package com.angiedev.rickmortyapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity (
    @PrimaryKey
    val id: Int,
    val status: Boolean,
    val image: String,
    val name:String,
    val selectedDate:String
){
    companion object {
        const val IS_ALIVE = "alive"
    }
    fun toDomain() = CharacterOfTheDayModel(
        character = CharacterModel(
            id = id,
            isAlive = status,
            image = image,
            name = name
        ),
        selectedDate = selectedDate
    )
}