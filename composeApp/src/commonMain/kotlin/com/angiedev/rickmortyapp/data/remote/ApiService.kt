package com.angiedev.rickmortyapp.data.remote

import com.angiedev.rickmortyapp.data.remote.responses.CharacterResponse
import com.angiedev.rickmortyapp.data.remote.responses.CharactersWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class ApiService(private val client: HttpClient) {

    suspend fun getCharacterById(id: Int): CharacterResponse =
        client.get("/api/character/$id").body<CharacterResponse>()

    suspend fun getAllCharacters(page:Int): CharactersWrapperResponse =
        client.get("/api/character"){
            parameter("page", page)
        }.body()
}
