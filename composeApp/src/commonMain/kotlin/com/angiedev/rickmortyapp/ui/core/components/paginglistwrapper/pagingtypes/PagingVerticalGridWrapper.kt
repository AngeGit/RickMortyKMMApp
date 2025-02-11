package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingVerticalGridWrapper(
    pagingListTitle: (@Composable () -> Unit)? = null,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    firstItemList: (@Composable () -> Unit)? = null,
) = LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.fillMaxWidth().padding(8.dp),
    verticalArrangement = Arrangement.Center,
    horizontalArrangement = Arrangement.Center
) {
    pagingListTitle?.let {
        item {
            pagingListTitle()
        }
    }

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