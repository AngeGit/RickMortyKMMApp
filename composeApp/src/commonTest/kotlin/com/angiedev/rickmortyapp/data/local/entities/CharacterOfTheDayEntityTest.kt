package com.angiedev.rickmortyapp.data.local.entities

import com.angiedev.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.angiedev.rickmortyapp.fixtures.aCharacter
import kotlin.test.Test
import kotlin.test.assertEquals

class CharacterOfTheDayEntityTest {

    private val characterOfTheDay = CharacterOfTheDayModel(
        character = aCharacter(id = 1, isAlive = false, episodes = listOf("1", "28")),
        selectedDate = "152025",
    )

    @Test
    fun `going to the entity and back keeps the character and the date`() {
        assertEquals(characterOfTheDay, characterOfTheDay.toEntity().toDomain())
    }

    @Test
    fun `stores the episodes as a JSON array`() {
        assertEquals("""["1","28"]""", characterOfTheDay.toEntity().episodes)
    }

    @Test
    fun `stores the status as a boolean`() {
        assertEquals(false, characterOfTheDay.toEntity().status)
    }
}
