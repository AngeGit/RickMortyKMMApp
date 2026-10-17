package com.angiedev.rickmortyapp.ui.core.components.paginglistwrapper.pagingdefaultviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.ui.core.components.ErrorView

/** Shown instead of the list when the first page fails to load. */
@Composable
fun PagingErrorView(onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ErrorView(onRetry = onRetry)
    }
}

/** Shown after the last item when the next page fails to load. */
@Composable
fun PagingItemErrorView(onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ErrorView(onRetry = onRetry, modifier = Modifier.widthIn(max = ITEM_ERROR_MAX_WIDTH))
    }
}

private val ITEM_ERROR_MAX_WIDTH = 280.dp
