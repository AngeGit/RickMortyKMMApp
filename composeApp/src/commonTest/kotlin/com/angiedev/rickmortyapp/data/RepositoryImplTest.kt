package com.angiedev.rickmortyapp.data

import androidx.paging.testing.asSnapshot
import com.angiedev.rickmortyapp.fakes.FakeApi
import com.angiedev.rickmortyapp.fakes.FakeUserPreferencesDao
import com.angiedev.rickmortyapp.fixtures.ApiJson
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RepositoryImplTest {

    @Test
    fun `the characters list loads the first page`() = runTest {
        val api = FakeApi { page -> ApiJson.charactersPage(page) }

        val characters = repositoryWith(api).getAllCharacters().asSnapshot()

        assertEquals(ApiJson.idsOnPage(1), characters.map { it.id }.take(2))
    }

    @Test
    fun `the characters list can be refreshed`() = runTest {
        val api = FakeApi { page -> ApiJson.charactersPage(page) }

        // A refresh invalidates the current PagingSource and asks the Pager for a new one
        val characters = repositoryWith(api).getAllCharacters().asSnapshot { refresh() }

        assertEquals(ApiJson.idsOnPage(1), characters.map { it.id }.take(2))
    }

    @Test
    fun `the episodes list loads the first page`() = runTest {
        val api = FakeApi { page -> ApiJson.episodesPage(page) }

        val episodes = repositoryWith(api).getAllEpisodes().asSnapshot()

        assertEquals(ApiJson.idsOnPage(1), episodes.map { it.id }.take(2))
    }

    @Test
    fun `the episodes list can be refreshed`() = runTest {
        val api = FakeApi { page -> ApiJson.episodesPage(page) }

        val episodes = repositoryWith(api).getAllEpisodes().asSnapshot { refresh() }

        assertEquals(ApiJson.idsOnPage(1), episodes.map { it.id }.take(2))
    }

    private fun repositoryWith(api: FakeApi) = RepositoryImpl(
        api = api.service,
        userPreferencesDao = FakeUserPreferencesDao(),
    )
}
