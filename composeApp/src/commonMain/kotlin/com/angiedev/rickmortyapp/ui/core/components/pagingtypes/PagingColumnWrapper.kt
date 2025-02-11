package com.angiedev.rickmortyapp.ui.core.components.pagingtypes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingColumnWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable (T) -> Unit)? = null,
    firstItemState: State<T>? = null,
) = LazyColumn {

    firstItemList?.let { itemView ->
        firstItemState?.value?.let { itemState ->
            item {
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