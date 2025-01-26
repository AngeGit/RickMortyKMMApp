package com.angiedev.rickmortyapp.domain.model

data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name:String,
)