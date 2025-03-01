package com.angiedev.rickmortyapp.data.remote

import com.angiedev.rickmortyapp.data.remote.responses.characters.CharacterResponse
import com.angiedev.rickmortyapp.data.remote.responses.characters.CharactersWrapperResponse
import com.angiedev.rickmortyapp.data.remote.responses.episodes.EpisodeResponse
import com.angiedev.rickmortyapp.data.remote.responses.episodes.EpisodesWrapperResponse
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

    suspend fun getAllEpisodes(page:Int): EpisodesWrapperResponse =
        client.get("/api/episode"){
            parameter("page", page)
        }.body()

    suspend fun getEpisodes(episodeList: String): List<EpisodeResponse> =
        client.get("/api/episode/$episodeList").body()

    suspend fun getSingleEpisode(episode: String): EpisodeResponse =
        client.get("/api/episode/$episode").body()
}
