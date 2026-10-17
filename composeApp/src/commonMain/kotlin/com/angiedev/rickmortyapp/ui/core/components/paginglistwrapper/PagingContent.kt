package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper

import androidx.paging.LoadState

/** What PagingGridWrapper shows in place of the list. */
internal enum class PagingContent {
    INITIAL_LOADING,
    EMPTY,
    ITEMS,
}

/** What PagingGridWrapper shows after the last item. */
internal enum class PagingFooter {
    NONE,
    LOADING,
}

internal fun pagingContentOf(refresh: LoadState, itemCount: Int): PagingContent = when {
    refresh is LoadState.Loading && itemCount == 0 -> PagingContent.INITIAL_LOADING
    refresh is LoadState.NotLoading && itemCount == 0 -> PagingContent.EMPTY
    else -> PagingContent.ITEMS
}

internal fun pagingFooterOf(append: LoadState): PagingFooter = when (append) {
    is LoadState.Loading -> PagingFooter.LOADING
    else -> PagingFooter.NONE
}
