package com.angiedev.rickmortyapp.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String
)