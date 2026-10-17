package com.angiedev.rickmortyapp.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.ui.core.resources.AppTypography
import com.angiedev.rickmortyapp.ui.core.resources.DefaultTextColor
import com.angiedev.rickmortyapp.ui.core.resources.HighlightBorderColor
import com.angiedev.rickmortyapp.ui.core.resources.Strings
import com.angiedev.rickmortyapp.ui.core.resources.primaryBlack
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorView(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = DefaultTextColor,
) {
    Column(
        modifier = modifier.padding(ERROR_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = Strings.ERROR_MESSAGE,
            color = textColor,
            style = AppTypography.regularXS,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(ERROR_SPACING))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = HighlightBorderColor),
        ) {
            Text(
                text = Strings.RETRY,
                color = primaryBlack,
                style = AppTypography.regularBoldXS,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorViewPreview() {
    ErrorView(onRetry = {})
}

private val ERROR_PADDING = 16.dp
private val ERROR_SPACING = 12.dp
