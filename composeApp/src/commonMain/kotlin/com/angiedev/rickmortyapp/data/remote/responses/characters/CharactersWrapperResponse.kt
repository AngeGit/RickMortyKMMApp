package com.angiedev.rickmortyapp.data.remote.responses.characters

import com.angiedev.rickmortyapp.data.remote.responses.InfoResponse
import kotlinx.serialization.Serializable

@Serializable
data class CharactersWrapperResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
){
}