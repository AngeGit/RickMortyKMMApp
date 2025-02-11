package com.angiedev.rickmortyapp.ui.core.components.pagingtypes

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingRowWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable (T) -> Unit)? = null,
    firstItemState: State<T>? = null,
) = LazyRow {

    firstItemList?.let { itemView ->
        firstItemState?.value?.let { itemState ->
            item {
                itemView(itemState)
            }
        }
    }

    //Default items list:
    items(pagingItems.itemCount) { pos ->
        pagingItems[pos]?.let {
            itemView(it)
        }
    }
}