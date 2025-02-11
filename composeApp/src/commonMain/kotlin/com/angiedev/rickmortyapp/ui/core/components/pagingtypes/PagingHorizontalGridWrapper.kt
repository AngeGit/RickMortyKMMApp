package com.angiedev.rickmortyapp.ui.core.components.pagingtypes

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingHorizontalGridWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable () -> Unit)? = null,
) = LazyHorizontalGrid(rows = GridCells.Fixed(2)) {

    firstItemList?.let {
        item(span = { GridItemSpan(2)  }){
            firstItemList()
        }
    }

    //Default items list:
    items(pagingItems.itemCount) { index ->
        pagingItems[index]?.let {
            itemView(it)
        }
    }
}