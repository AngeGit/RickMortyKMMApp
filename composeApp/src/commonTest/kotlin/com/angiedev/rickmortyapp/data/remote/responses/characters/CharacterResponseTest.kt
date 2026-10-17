package com.angiedev.rickmortyapp.data.remote.responses.characters

import com.angiedev.rickmortyapp.fixtures.aCharacter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharacterResponseTest {

    @Test
    fun `maps every field to the domain model`() {
        val response = aCharacterResponse(
            id = 2,
            status = "Alive",
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            name = "Morty Smith",
            species = "Human",
            gender = "Male",
            origin = OriginResponse(name = "unknown", url = ""),
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
            ),
        )

        assertEquals(
            aCharacter(
                id = 2,
                isAlive = true,
                image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                name = "Morty Smith",
                species = "Human",
                gender = "Male",
                origin = "unknown",
                episodes = listOf("1", "2"),
            ),
            response.toDomain(),
        )
    }

    @Test
    fun `alive status is case insensitive`() {
        assertTrue(aCharacterResponse(status = "Alive").toDomain().isAlive)
        assertTrue(aCharacterResponse(status = "ALIVE").toDomain().isAlive)
    }

    @Test
    fun `dead and unknown statuses are not alive`() {
        assertFalse(aCharacterResponse(status = "Dead").toDomain().isAlive)
        assertFalse(aCharacterResponse(status = "unknown").toDomain().isAlive)
    }

    @Test
    fun `keeps only the id of each episode url`() {
        val response = aCharacterResponse(
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/10",
                "https://rickandmortyapi.com/api/episode/51",
            ),
        )

        assertEquals(listOf("10", "51"), response.toDomain().episodes)
    }

    private fun aCharacterResponse(
        id: Int = 1,
        status: String = "Alive",
        image: String = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        name: String = "Rick Sanchez",
        species: String = "Human",
        gender: String = "Male",
        origin: OriginResponse = OriginResponse(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1",
        ),
        episode: List<String> = listOf("https://rickandmortyapi.com/api/episode/1"),
    ) = CharacterResponse(
        id = id,
        status = status,
        image = image,
        name = name,
        species = species,
        gender = gender,
        origin = origin,
        episode = episode,
    )
}
