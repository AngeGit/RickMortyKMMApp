package com.angiedev.rickmortyapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.angiedev.rickmortyapp.domain.model.CharacterModel

@Composable
fun CharacterDetailScreen(
    characterModel: CharacterModel
) {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Red)) {
        Text(text = "Character Detail Screen")
        Text(text = "character Name = ${characterModel.name}")
    }
}