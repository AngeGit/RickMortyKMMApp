package com.angiedev.rickmortyapp.data.remote.responses.episodes

import com.angiedev.rickmortyapp.domain.model.SeasonEpisode
import com.angiedev.rickmortyapp.fixtures.anEpisode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodeResponseTest {

    @Test
    fun `maps every field to the domain model`() {
        val response = anEpisodeResponse(
            id = 28,
            name = "The Ricklantis Mixup",
            episode = "S03E07",
            characters = listOf(
                "https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2",
            ),
            url = "https://rickandmortyapi.com/api/episode/28",
        )

        assertEquals(
            anEpisode(
                id = 28,
                name = "The Ricklantis Mixup",
                episode = "S03E07",
                characters = listOf("1", "2"),
                url = "https://rickandmortyapi.com/api/episode/28",
                videoUrl = "rLyOul8kau0",
                season = SeasonEpisode.SEASON_3,
            ),
            response.toDomain(),
        )
    }

    @Test
    fun `reads the season from the episode code`() {
        val seasons = (1..7).map { season -> anEpisodeResponse(episode = "S0${season}E01").toDomain().season }

        assertEquals(
            listOf(
                SeasonEpisode.SEASON_1,
                SeasonEpisode.SEASON_2,
                SeasonEpisode.SEASON_3,
                SeasonEpisode.SEASON_4,
                SeasonEpisode.SEASON_5,
                SeasonEpisode.SEASON_6,
                SeasonEpisode.SEASON_7,
            ),
            seasons,
        )
    }

    @Test
    fun `every known season has a video`() {
        val videos = (1..7).map { season -> anEpisodeResponse(episode = "S0${season}E01").toDomain().videoUrl }

        assertTrue(videos.none { it.isBlank() })
    }

    @Test
    fun `an unknown episode code has no season and no video`() {
        val episode = anEpisodeResponse(episode = "S08E01").toDomain()

        assertEquals(SeasonEpisode.UNKNOWN, episode.season)
        assertEquals("", episode.videoUrl)
    }

    private fun anEpisodeResponse(
        id: Int = 1,
        name: String = "Pilot",
        airDate: String = "December 2, 2013",
        episode: String = "S01E01",
        characters: List<String> = listOf("https://rickandmortyapi.com/api/character/1"),
        url: String = "https://rickandmortyapi.com/api/episode/1",
        created: String = "2017-11-10T12:56:33.798Z",
    ) = EpisodeResponse(
        id = id,
        name = name,
        air_date = airDate,
        episode = episode,
        characters = characters,
        url = url,
        created = created,
    )
}
