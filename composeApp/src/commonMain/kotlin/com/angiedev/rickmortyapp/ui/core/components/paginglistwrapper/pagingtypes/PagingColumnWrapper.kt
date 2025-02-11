package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingColumnWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable () -> Unit)? = null,
) = LazyColumn {

    firstItemList?.let {
        item {
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