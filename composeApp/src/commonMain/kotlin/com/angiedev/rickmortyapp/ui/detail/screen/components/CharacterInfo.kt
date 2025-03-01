package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.angiedev.rickmortyapp.domain.model.CharacterModel


@Composable
fun CharacterInfo(characterModel: CharacterModel, modifier: Modifier) {
    CharacterCard(modifier) {
        Text(text = "ABOUT THE CHARACTER", fontWeight = FontWeight.Bold)
        CustomSpacer()
        CharacterInfoDetail("Origin", characterModel.origin)
        CharacterInfoDetail("Gender", characterModel.gender)
    }
}

@Composable
fun CharacterInfoDetail(title: String, detail: String) {
    Row {
        Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = detail, color = Color.Green)
    }
}


