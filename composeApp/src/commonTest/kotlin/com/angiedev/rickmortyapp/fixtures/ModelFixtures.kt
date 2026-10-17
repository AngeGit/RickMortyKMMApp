package com.angiedev.rickmortyapp.fixtures

import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.domain.model.SeasonEpisode

fun aCharacter(
    id: Int = 1,
    isAlive: Boolean = true,
    image: String = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg",
    name: String = "Rick Sanchez",
    species: String = "Human",
    gender: String = "Male",
    origin: String = "Earth (C-137)",
    episodes: List<String> = listOf("1", "2"),
) = CharacterModel(
    id = id,
    isAlive = isAlive,
    image = image,
    name = name,
    species = species,
    gender = gender,
    origin = origin,
    episodes = episodes,
)

fun anEpisode(
    id: Int = 1,
    name: String = "Pilot",
    episode: String = "S01E01",
    characters: List<String> = listOf("1", "2"),
    url: String = "https://rickandmortyapi.com/api/episode/$id",
    videoUrl: String = "bLI2-v264No",
    season: SeasonEpisode = SeasonEpisode.SEASON_1,
) = EpisodeModel(
    id = id,
    name = name,
    episode = episode,
    characters = characters,
    url = url,
    videoUrl = videoUrl,
    season = season,
)
