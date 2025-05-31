package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingtypes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingRowWrapper(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    progress: ((Float) -> Unit)? = null,
    firstItemList: (@Composable () -> Unit)? = null
) {
    val scrollState = rememberLazyListState()

    progress?.invoke(
        (scrollState.firstVisibleItemIndex.toFloat() / (scrollState.layoutInfo.totalItemsCount - 1))
            .takeIf { scrollState.layoutInfo.totalItemsCount > 1 } ?: 0f
    )

    LazyRow(
        state = scrollState,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        firstItemList?.let {
            item {
                firstItemList()
            }
        }

        //Default items list:
        items(pagingItems.itemCount) { pos ->
            pagingItems[pos]?.let { item ->
                itemView(item)
            }
        }
    }
}