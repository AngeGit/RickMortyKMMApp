package com.angiedev.rickmortyapp.data.remote.responses

data class CharactersWrapperResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
){
}