package com.angiedev.rickmortyapp.data.remote

import com.angiedev.rickmortyapp.data.remote.responses.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class ApiService(private val client: HttpClient) {

    suspend fun getCharacterById(id: Int): CharacterResponse =
        client.get("/api/character/$id").body<CharacterResponse>()
}