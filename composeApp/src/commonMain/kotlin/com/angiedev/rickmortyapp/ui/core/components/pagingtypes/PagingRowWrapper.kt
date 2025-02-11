package com.angiedev.rickmortyapp.ui.core.components.pagingtypes

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingRowWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable () -> Unit)? = null,
) = LazyRow {
    firstItemList?.let {
        item {
            firstItemList()
        }
    }

    //Default items list:
    items(pagingItems.itemCount) { pos ->
        pagingItems[pos]?.let { item->
            itemView(item)
        }
    }
}