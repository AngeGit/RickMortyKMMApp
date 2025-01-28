package com.angiedev.rickmortyapp.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class CharactersWrapperResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
){
}