package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.ui.core.resources.AppTypography
import com.angiedev.rickmortyapp.ui.core.resources.BackgroundTertiaryColor
import com.angiedev.rickmortyapp.ui.core.resources.DefaultTextColor


@Composable
fun CharacterCard(modifier: Modifier, content: @Composable () -> Unit) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor),
        shape = RoundedCornerShape(CHARACTER_CARD_ROUNDED_CORNER)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            content()
        }
    }
}

@Composable
fun CharacterDetailTitle(text: String) =
    Text(
        text = text.toUpperCase(locale = Locale.current),
        color = DefaultTextColor,
        style = AppTypography.regularBoldS
    )

@Composable
fun CustomVerticalSpacer() =
    Spacer(modifier = Modifier.height(16.dp))


const val CHARACTER_CARD_ROUNDED_CORNER = 10
val DETAIL_ARRANGEMENT_SPACING = 16.dp