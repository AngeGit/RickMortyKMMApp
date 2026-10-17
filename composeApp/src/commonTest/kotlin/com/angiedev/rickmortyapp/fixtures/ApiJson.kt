package com.angiedev.rickmortyapp.fixtures

/**
 * JSON with the shape rickandmortyapi.com returns, including fields the app ignores, so the tests
 * also cover `ignoreUnknownKeys`. Each page has two items with ids derived from the page number.
 */
object ApiJson {

    const val LAST_PAGE = 42

    fun idsOnPage(page: Int) = listOf(page * 10 + 1, page * 10 + 2)

    fun charactersPage(page: Int, lastPage: Int = LAST_PAGE) = """
        {
          "info": ${info(resource = "character", page = page, lastPage = lastPage, count = 826)},
          "results": [${idsOnPage(page).joinToString(",") { character(it) }}]
        }
    """.trimIndent()

    fun episodesPage(page: Int, lastPage: Int = LAST_PAGE) = """
        {
          "info": ${info(resource = "episode", page = page, lastPage = lastPage, count = 51)},
          "results": [${idsOnPage(page).joinToString(",") { episode(it) }}]
        }
    """.trimIndent()

    private fun info(resource: String, page: Int, lastPage: Int, count: Int): String {
        val next = if (page < lastPage) "\"https://rickandmortyapi.com/api/$resource?page=${page + 1}\"" else "null"
        val prev = if (page > 1) "\"https://rickandmortyapi.com/api/$resource?page=${page - 1}\"" else "null"
        return """{ "count": $count, "pages": $lastPage, "next": $next, "prev": $prev }"""
    }

    private fun character(id: Int) = """
        {
          "id": $id,
          "name": "Character $id",
          "status": "Alive",
          "species": "Human",
          "type": "",
          "gender": "Female",
          "origin": { "name": "Earth (C-137)", "url": "https://rickandmortyapi.com/api/location/1" },
          "location": { "name": "Citadel of Ricks", "url": "https://rickandmortyapi.com/api/location/3" },
          "image": "https://rickandmortyapi.com/api/character/avatar/$id.jpeg",
          "episode": ["https://rickandmortyapi.com/api/episode/1"],
          "url": "https://rickandmortyapi.com/api/character/$id",
          "created": "2017-11-04T18:48:46.250Z"
        }
    """.trimIndent()

    private fun episode(id: Int) = """
        {
          "id": $id,
          "name": "Episode $id",
          "air_date": "December 2, 2013",
          "episode": "S01E01",
          "characters": ["https://rickandmortyapi.com/api/character/1"],
          "url": "https://rickandmortyapi.com/api/episode/$id",
          "created": "2017-11-10T12:56:33.798Z"
        }
    """.trimIndent()
}
