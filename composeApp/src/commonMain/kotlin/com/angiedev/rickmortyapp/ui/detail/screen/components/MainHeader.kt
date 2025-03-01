package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.extensions.aliveBorder
import org.jetbrains.compose.resources.painterResource
import rickmortykmmapp.composeapp.generated.resources.Res
import rickmortykmmapp.composeapp.generated.resources.space


@Composable
fun MainHeader(characterModel: CharacterModel) {
    val mainHeaderModifier = Modifier.fillMaxWidth().height(300.dp)

    Box(modifier = mainHeaderModifier) {
        Image(
            painterResource(Res.drawable.space),
            contentDescription = "Background Header",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CharacterHeader(characterModel, mainHeaderModifier)
    }
}

@Composable
fun CharacterHeader(characterModel: CharacterModel, modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        CharacterTitleBox(characterModel.name, characterModel.species)
        CharacterImageBox(characterModel.image, characterModel.isAlive)
    }
}

@Composable
fun CharacterImageBox(image: String, isAlive: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomSpacer()
        Box(contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier.size(205.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(0.15f)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Character Image",
                    modifier = Modifier.size(190.dp)
                        .clip(CircleShape)
                        .aliveBorder(isAlive),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                "ALIVE".takeIf { isAlive } ?: "DEAD",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(30))
                    .background(Color.Green.takeIf { isAlive } ?: Color.Red)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CharacterTitleBox(
    characterName: String,
    characterSpecies: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            characterName,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text("Especie: $characterSpecies", color = Color.Black)
    }
}