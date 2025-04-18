package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.ui.core.BackgroundTertiaryColor
import com.angiedev.rickmortyapp.ui.core.DefaultTextColor


@Composable
fun CharacterCard(modifier: Modifier, content: @Composable () -> Unit) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}
@Composable
fun CharacterDetailTitle(text:String) =
    Text(text = text.toUpperCase(locale = Locale.current), color = DefaultTextColor, fontWeight = FontWeight.Bold)

@Composable
fun CustomVerticalSpacer() =
    Spacer(modifier = Modifier.height(16.dp))

@Composable
fun CustomHorizontalSpacer() =
    Spacer(modifier = Modifier.width(16.dp))