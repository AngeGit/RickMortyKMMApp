package com.angiedev.rickmortyapp.data.remote.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.fakes.FakeApi
import com.angiedev.rickmortyapp.fixtures.ApiJson
import com.angiedev.rickmortyapp.fixtures.anEpisode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

class EpisodesPagingSourceTest {

    private val api = FakeApi { page -> ApiJson.episodesPage(page) }
    private val pagingSource = EpisodesPagingSource(api.service)

    //region load
    @Test
    fun `the first load requests page 1`() = runTest {
        pagingSource.load(refresh(key = null))

        assertEquals(listOf(1), api.requestedPages)
    }

    @Test
    fun `maps the page results to domain models`() = runTest {
        val page = assertIs<LoadResult.Page<Int, EpisodeModel>>(pagingSource.load(append(key = 3)))

        assertEquals(ApiJson.idsOnPage(3), page.data.map { it.id })
    }

    @Test
    fun `the first page has no previous page`() = runTest {
        val page = assertIs<LoadResult.Page<Int, EpisodeModel>>(pagingSource.load(refresh(key = null)))

        assertNull(page.prevKey)
    }

    @Test
    fun `a later page points to the page before it`() = runTest {
        val page = assertIs<LoadResult.Page<Int, EpisodeModel>>(pagingSource.load(append(key = 3)))

        assertEquals(2, page.prevKey)
    }

    @Test
    fun `a page points to the next one while the API has more`() = runTest {
        val page = assertIs<LoadResult.Page<Int, EpisodeModel>>(pagingSource.load(append(key = 3)))

        assertEquals(4, page.nextKey)
    }

    @Test
    fun `the last page has no next page`() = runTest {
        val page = assertIs<LoadResult.Page<Int, EpisodeModel>>(pagingSource.load(append(key = ApiJson.LAST_PAGE)))

        assertNull(page.nextKey)
    }

    @Test
    fun `a failed request becomes a load error`() = runTest {
        api.error = IllegalStateException("No network")

        assertIs<LoadResult.Error<Int, EpisodeModel>>(pagingSource.load(refresh(key = null)))
    }
    //endregion load

    //region getRefreshKey
    @Test
    fun `without an anchor there is no refresh key`() {
        assertNull(pagingSource.getRefreshKey(stateOf(anchorPosition = null)))
    }

    @Test
    fun `the refresh key is the page that holds the anchor`() {
        // Item 25 lives in the second page (items 20..39)
        assertEquals(2, pagingSource.getRefreshKey(stateOf(anchorPosition = 25)))
    }

    @Test
    fun `an anchor in the first page refreshes from page 1`() {
        assertEquals(1, pagingSource.getRefreshKey(stateOf(anchorPosition = 5)))
    }
    //endregion getRefreshKey

    private fun refresh(key: Int?) = LoadParams.Refresh(key = key, loadSize = PAGE_SIZE, placeholdersEnabled = false)

    private fun append(key: Int) = LoadParams.Append(key = key, loadSize = PAGE_SIZE, placeholdersEnabled = false)

    // Two loaded pages of PAGE_SIZE items each, with the keys Paging would hold for pages 1 and 2
    private fun stateOf(anchorPosition: Int?) = PagingState(
        pages = listOf(
            LoadResult.Page(data = List(PAGE_SIZE) { anEpisode(id = it + 1) }, prevKey = null, nextKey = 2),
            LoadResult.Page(data = List(PAGE_SIZE) { anEpisode(id = PAGE_SIZE + it + 1) }, prevKey = 1, nextKey = 3),
        ),
        anchorPosition = anchorPosition,
        config = PagingConfig(pageSize = PAGE_SIZE),
        leadingPlaceholderCount = 0,
    )

    private companion object {
        const val PAGE_SIZE = 20
    }
}
