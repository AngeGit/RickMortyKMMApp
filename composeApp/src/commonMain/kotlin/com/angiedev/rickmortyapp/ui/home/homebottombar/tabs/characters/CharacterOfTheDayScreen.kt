package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.extensions.vertical


@Composable
fun CharacterOfTheDayComponent(characterOfTheDay: CharacterModel? = null) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp).padding(16.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        characterOfTheDay?.let {
            Box(contentAlignment = Alignment.BottomStart) {
                Box(
                    Modifier.fillMaxSize()
                        .background(Color.Green.copy(alpha = 0.5f))
                )

                AsyncImage(
                    model = it.image,
                    contentDescription = "Character of the day:${it.name}",
                    contentScale= ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    Modifier.fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                0f to Color.Black.copy(alpha = 0.9f),
                                0.4f to Color.Transparent,
                                1f to Color.Black.copy(alpha = 0f)
                            )
                        )
                )
                Text(
                    characterOfTheDay.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(-90f)
                )

            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }

}