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
            SeasonEpisode.SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmm.firebasestorage.app/o/RICK%20Y%20MORTY%20(Trailer%20espa%C3%B1ol).mp4?alt=media&token=3e6763c9-df73-46e4-856d-511bdff1d8c2"
            SeasonEpisode.SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmm.firebasestorage.app/o/RICK%20Y%20MORTY%20(Trailer%20espa%C3%B1ol).mp4?alt=media&token=3e6763c9-df73-46e4-856d-511bdff1d8c2"
            SeasonEpisode.SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmm.firebasestorage.app/o/RICK%20Y%20MORTY%20(Trailer%20espa%C3%B1ol).mp4?alt=media&token=3e6763c9-df73-46e4-856d-511bdff1d8c2"
            SeasonEpisode.SEASON_4 -> "https://www.youtube.com/watch?v=bLI2-v264No"
            SeasonEpisode.SEASON_5 -> "https://www.youtube.com/watch?v=yC1UxW8vcDo&ab_channel=RottenTomatoesTV"
            SeasonEpisode.SEASON_6 -> "https://www.youtube.com/watch?v=jerFRSQW9g8&ab_channel=RottenTomatoesTV"
            SeasonEpisode.SEASON_7 -> "https://www.youtube.com/watch?v=PkZtVBNkmso&ab_channel=RottenTomatoesTV"
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