package com.angiedev.rickmortyapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name:String,
)