package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CharacterCard(modifier: Modifier, content: @Composable () -> Unit) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}

@Composable
fun CustomSpacer() =
    Spacer(modifier = Modifier.height(16.dp))