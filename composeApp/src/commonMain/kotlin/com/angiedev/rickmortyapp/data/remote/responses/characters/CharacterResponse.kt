package com.angiedev.rickmortyapp.data.remote.responses.characters

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String,
    val name:String,
    val species: String,
    val gender: String,
    val origin: OriginResponse,
    val episode: List<String>
) {
    companion object {
        const val IS_ALIVE = "alive"
    }

    fun toDomain(): CharacterModel = CharacterModel(
        id = id,
        isAlive = status.lowercase() == IS_ALIVE,
        image = image,
        name = name,
        species = species,
        gender = gender,
        origin = origin.name,
        episodes = episode.map {
            it.substringAfterLast("/")
        }
    )
}