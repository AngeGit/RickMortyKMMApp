package com.angiedev.rickmortyapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity (
    @PrimaryKey
    val id: Int,
    val status: String,
    val image: String,
    val name:String,
    val selectedDate:String
)