package com.angiedev.rickmortyapp.data.remote.responses

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String,
    val name:String,
) {
    companion object {
        const val IS_ALIVE = "alive"
    }

    fun toDomain(): CharacterModel = CharacterModel(
        id = id,
        isAlive = status.lowercase() == IS_ALIVE,
        image = image,
        name = name
    )
}