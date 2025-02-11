package com.angiedev.rickmortyapp.data.remote.responses.episodes


import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.domain.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
){
    fun toDomain() : EpisodeModel {
        val seasonEpisode = getSeasonFromEpisodeCode(episode)

       return  EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            url = url,
            characters = characters.map {
                it.substringAfterLast("/")
            },
            videoUrl = getVideoUrlFromSeasonEpisode(seasonEpisode),
            season = seasonEpisode,
        )
    }

    private fun getVideoUrlFromSeasonEpisode(seasonEpisode: SeasonEpisode): String =
        when (seasonEpisode) {
            SeasonEpisode.SEASON_1 -> "bLI2-v264No"
            SeasonEpisode.SEASON_2 -> "6Hb7VdgW1nc"
            SeasonEpisode.SEASON_3 -> "rLyOul8kau0"
            SeasonEpisode.SEASON_4 -> "hl1U0bxTHbY"
            SeasonEpisode.SEASON_5 -> "qbHYYXj2gMc"
            SeasonEpisode.SEASON_6 -> "jerFRSQW9g8"
            SeasonEpisode.SEASON_7 -> "PkZtVBNkmso"
            SeasonEpisode.UNKNOWN -> ""
        }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode =
        when {
            episode.startsWith("S01") -> SeasonEpisode.SEASON_1
            episode.startsWith("S02") -> SeasonEpisode.SEASON_2
            episode.startsWith("S03") -> SeasonEpisode.SEASON_3
            episode.startsWith("S04") -> SeasonEpisode.SEASON_4
            episode.startsWith("S05") -> SeasonEpisode.SEASON_5
            episode.startsWith("S06") -> SeasonEpisode.SEASON_6
            episode.startsWith("S07") -> SeasonEpisode.SEASON_7
            else -> SeasonEpisode.UNKNOWN

        }

}