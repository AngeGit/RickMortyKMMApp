package com.angiedev.rickmortyapp.ui.core.components

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.angiedev.rickmortyapp.ui.core.components.pagingdefaultviews.PagingEmptyView
import com.angiedev.rickmortyapp.ui.core.components.pagingdefaultviews.PagingInitialLoadingView
import com.angiedev.rickmortyapp.ui.core.components.pagingdefaultviews.PagingItemLoadingView
import com.angiedev.rickmortyapp.ui.core.components.pagingtypes.PagingColumnWrapper
import com.angiedev.rickmortyapp.ui.core.components.pagingtypes.PagingHorizontalGridWrapper
import com.angiedev.rickmortyapp.ui.core.components.pagingtypes.PagingRowWrapper
import com.angiedev.rickmortyapp.ui.core.components.pagingtypes.PagingVerticalGridWrapper


@Composable
fun <T : Any> PagingGridWrapper(
    pagingListTitle: (@Composable () -> Unit)? = null,
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    initialLoadingView: @Composable () -> Unit = { PagingInitialLoadingView() },
    emptyListView: @Composable () -> Unit = { PagingEmptyView() },
    firstItemView: (@Composable () -> Unit)? = null,
    itemLoadingView: @Composable () -> Unit = { PagingItemLoadingView() },
    ) {

    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            initialLoadingView()
        }

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            emptyListView()
        }

        else -> {
            when(pagingType){
                PagingType.ROW -> {
                    PagingRowWrapper(
                        pagingItems = pagingItems,
                        firstItemList = firstItemView,
                        itemView = itemView
                    )
                }
                PagingType.COLUMN -> {
                    PagingColumnWrapper(
                        pagingItems = pagingItems,
                        firstItemList = firstItemView,
                        itemView = itemView
                    )
                }
                PagingType.VERITCAL_GRID -> {
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

            //Loading
            if (pagingItems.loadState.append is LoadState.Loading) {
                itemLoadingView()
            }
        }
    }
}


enum class PagingType {
    ROW,
    COLUMN,
    VERITCAL_GRID,
    HORIZONTAL_GRID
}
