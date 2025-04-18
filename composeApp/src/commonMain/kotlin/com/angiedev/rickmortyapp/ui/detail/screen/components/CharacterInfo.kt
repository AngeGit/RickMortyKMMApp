package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.core.DefaultTextColor
import com.angiedev.rickmortyapp.ui.core.Green


@Composable
fun CharacterInfo(characterModel: CharacterModel, modifier: Modifier) {
    CharacterCard(modifier) {
        CharacterDetailTitle(text = "ABOUT THE CHARACTER")
        CustomVerticalSpacer()
        CharacterInfoDetail("Origin", characterModel.origin)
        CharacterInfoDetail("Gender", characterModel.gender)
    }
}

@Composable
fun CharacterInfoDetail(title: String, detail: String) {
    Row {
        Text(text = title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        CustomHorizontalSpacer()
        Text(text = detail, color = Green)
    }
}


