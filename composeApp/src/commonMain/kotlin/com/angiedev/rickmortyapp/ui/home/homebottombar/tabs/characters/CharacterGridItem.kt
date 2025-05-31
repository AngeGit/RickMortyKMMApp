package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.core.resources.AppTypography
import com.angiedev.rickmortyapp.ui.core.resources.Strings
import com.angiedev.rickmortyapp.ui.core.resources.primaryWhite

@Composable
fun CharacterItemList(
    character: CharacterModel,
    onItemSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(ITEM_PADDING)
            .clip(ITEM_SHAPE)
            .border(
                width = ITEM_BORDER_WIDTH,
                color = ITEM_BORDER_COLOR,
                shape = ITEM_SHAPE
            )
            .clickable { onItemSelected() },
        contentAlignment = Alignment.BottomCenter
    ) {
        CharacterImage(character)
        CharacterName(character.name)
    }
}

@Composable
private fun CharacterImage(character: CharacterModel) {
    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = character.image,
        contentDescription = Strings.cdCharacterOfTheDay(character.name),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun CharacterName(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(NAME_OVERLAY_HEIGHT)
            .background(NAME_OVERLAY_GRADIENT),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = primaryWhite,
            style = AppTypography.regularXS,
            modifier = Modifier.padding(NAME_PADDING)
        )
    }
}

// Constantes
private val ITEM_PADDING = 8.dp
private val ITEM_SHAPE = RoundedCornerShape(0, 24, 0, 24)
private val ITEM_BORDER_WIDTH = 2.dp
private val ITEM_BORDER_COLOR = Color.Green
private val NAME_OVERLAY_HEIGHT = 60.dp
private val NAME_PADDING = 8.dp
private val NAME_OVERLAY_GRADIENT = Brush.verticalGradient(
    colors = listOf(
        Color.Black.copy(alpha = 0f),
        Color.Black.copy(alpha = 0.6f),
        Color.Black.copy(alpha = 0.9f)
    )
)