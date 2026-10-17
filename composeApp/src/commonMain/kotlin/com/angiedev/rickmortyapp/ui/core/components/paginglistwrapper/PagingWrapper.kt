package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper

import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems
import com.angiedev.rickmortyapp.ui.core.components.Loading
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingdefaultviews.PagingEmptyView
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingdefaultviews.PagingItemLoadingView
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes.PagingColumnWrapper
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes.PagingHorizontalGridWrapper
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes.PagingRowWrapper
import com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes.PagingVerticalGridWrapper


@Composable
fun <T : Any> PagingGridWrapper(
    pagingListTitle: (@Composable () -> Unit)? = null,
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    progress: ((Float) -> Unit)? = null,
    initialLoadingView: @Composable () -> Unit = { Loading() },
    emptyListView: @Composable () -> Unit = { PagingEmptyView() },
    firstItemView: (@Composable () -> Unit)? = null,
    itemLoadingView: @Composable () -> Unit = { PagingItemLoadingView() },
    ) {

    when (pagingContentOf(pagingItems.loadState.refresh, pagingItems.itemCount)) {
        PagingContent.INITIAL_LOADING -> {
            initialLoadingView()
        }

        PagingContent.EMPTY -> {
            emptyListView()
        }

        PagingContent.ERROR -> Unit

        PagingContent.ITEMS -> {
            when(pagingType){
                PagingType.ROW -> {
                    PagingRowWrapper(
                        pagingItems = pagingItems,
                        firstItemList = firstItemView,
                        itemView = itemView,
                        progress = progress
                    )
                }
                PagingType.COLUMN -> {
                    PagingColumnWrapper(
                        pagingItems = pagingItems,
                        firstItemList = firstItemView,
                        itemView = itemView
                    )
                }
                PagingType.VERTICAL_GRID -> {
                    PagingVerticalGridWrapper(
                        pagingListTitle = pagingListTitle,
                        pagingItems = pagingItems,
                        firstItemList = firstItemView,
                        itemView = itemView
                    )
                }
                PagingType.HORIZONTAL_GRID -> {
                    PagingHorizontalGridWrapper(
                        pagingItems = pagingItems,
                        firstItemList = firstItemView,
                        itemView = itemView
                    )
                }
            }

            when (pagingFooterOf(pagingItems.loadState.append)) {
                PagingFooter.LOADING -> itemLoadingView()
                PagingFooter.ERROR -> Unit
                PagingFooter.NONE -> Unit
            }
        }
    }
}

enum class PagingType {
    ROW,
    COLUMN,
    VERTICAL_GRID,
    HORIZONTAL_GRID
}