package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.core.components.Loading
import com.angiedev.rickmortyapp.ui.core.resources.AppTypography
import com.angiedev.rickmortyapp.ui.core.resources.BrightGreen
import com.angiedev.rickmortyapp.ui.core.resources.Strings
import com.angiedev.rickmortyapp.ui.core.resources.primaryBlack
import com.angiedev.rickmortyapp.ui.core.resources.primaryWhite
import com.angiedev.rickmortyapp.ui.extensions.vertical


@Composable
fun CharacterOfTheDayComponent(characterOfTheDay: CharacterModel? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(CHARACTER_CARD_HEIGHT)
            .padding(CARD_PADDING),
        shape = RoundedCornerShape(CARD_CORNER_RADIUS),
    ) {
        characterOfTheDay?.let {
            CharacterContent(characterOfTheDay)
        } ?: Loading()
    }
}

@Composable
private fun CharacterContent(character: CharacterModel) {
    Box(contentAlignment = Alignment.BottomStart) {
        AsyncImage(
            model = character.image,
            contentDescription = Strings.cdCharacterOfTheDay(character.name),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        CharacterOfTheDayName(character.name)
    }
}

@Composable
private fun CharacterOfTheDayName(name: String) {
    Box(
        Modifier.fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    0f to primaryBlack.copy(alpha = 0.9f),
                    0.4f to Color.Transparent,
                    1f to primaryBlack.copy(alpha = 0f)
                )
            )
    )

    Text(
        name,
        maxLines = 1,
        minLines = 1,
        textAlign = TextAlign.Center,
        style = AppTypography.regularXL,
        color = primaryWhite,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = TEXT_HORIZONTAL_PADDING, vertical = TEXT_VERTICAL_PADDING)
            .fillMaxHeight()
            .vertical()
            .rotate(TEXT_ROTATION_ANGLE)
    )
}


private val CHARACTER_CARD_HEIGHT = 400.dp
private val CARD_PADDING = 16.dp
private val CARD_CORNER_RADIUS = 12.dp
private val TEXT_HORIZONTAL_PADDING = 24.dp
private val TEXT_VERTICAL_PADDING = 32.dp
private const val TEXT_ROTATION_ANGLE = -90f