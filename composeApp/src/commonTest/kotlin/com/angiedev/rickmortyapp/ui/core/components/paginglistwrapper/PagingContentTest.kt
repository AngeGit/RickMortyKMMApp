package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper

import androidx.paging.LoadState
import kotlin.test.Test
import kotlin.test.assertEquals

class PagingContentTest {

    private val loaded = LoadState.NotLoading(endOfPaginationReached = false)
    private val failed = LoadState.Error(IllegalStateException("No network"))

    //region pagingContentOf
    @Test
    fun `the first load shows the loading view`() {
        assertEquals(PagingContent.INITIAL_LOADING, pagingContentOf(LoadState.Loading, itemCount = 0))
    }

    @Test
    fun `a finished load with no items shows the empty view`() {
        assertEquals(PagingContent.EMPTY, pagingContentOf(loaded, itemCount = 0))
    }

    @Test
    fun `a finished load with items shows the items`() {
        assertEquals(PagingContent.ITEMS, pagingContentOf(loaded, itemCount = 20))
    }

    @Test
    fun `a reload with items already on screen keeps showing them`() {
        assertEquals(PagingContent.ITEMS, pagingContentOf(LoadState.Loading, itemCount = 20))
    }

    @Test
    fun `a failed reload with items already on screen keeps showing them`() {
        assertEquals(PagingContent.ITEMS, pagingContentOf(failed, itemCount = 20))
    }
    //endregion pagingContentOf

    //region pagingFooterOf
    @Test
    fun `loading the next page shows the loading footer`() {
        assertEquals(PagingFooter.LOADING, pagingFooterOf(LoadState.Loading))
    }

    @Test
    fun `with no page loading there is no footer`() {
        assertEquals(PagingFooter.NONE, pagingFooterOf(loaded))
    }
    //endregion pagingFooterOf
}
