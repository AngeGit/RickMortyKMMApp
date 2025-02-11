package com.angiedev.rickmortyapp.ui.core.components.pagingtypes

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingHorizontalGridWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable (T) -> Unit)? = null,
    firstItemState: State<T>? = null,
) = LazyHorizontalGrid(rows = GridCells.Fixed(2)) {

    firstItemList?.let { itemView ->
        firstItemState?.value?.let { itemState ->
            item (span = { GridItemSpan(2) }){
                itemView(itemState)
            }
        }
    }

    //Default items list:
    items(pagingItems.itemCount) { index ->
        pagingItems[index]?.let {
            itemView(it)
        }
    }
}