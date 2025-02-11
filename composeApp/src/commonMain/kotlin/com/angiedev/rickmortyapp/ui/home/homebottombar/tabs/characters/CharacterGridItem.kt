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

@Composable
fun CharacterItemList(character: CharacterModel) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(
                2.dp,
                Color.Green,
                shape = RoundedCornerShape(0,24,0,24)
            ).fillMaxSize()
            .clickable {
                //go to Detail
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character:${character.name}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = character.name,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
