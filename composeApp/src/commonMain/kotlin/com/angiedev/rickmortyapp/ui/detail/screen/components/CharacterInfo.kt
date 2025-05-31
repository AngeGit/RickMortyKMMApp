package com.angiedev.rickmortyapp.ui.detail.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.core.resources.AppTypography
import com.angiedev.rickmortyapp.ui.core.resources.DefaultTextColor
import com.angiedev.rickmortyapp.ui.core.resources.Green
import com.angiedev.rickmortyapp.ui.core.resources.Strings


@Composable
fun CharacterInfo(characterModel: CharacterModel, modifier: Modifier) {
    CharacterCard(modifier) {
        CharacterDetailTitle(text = Strings.CHARACTER_DETAIL_ABOUT)
        CustomVerticalSpacer()
        CharacterInfoDetail(Strings.CHARACTER_DETAIL_ORIGIN, characterModel.origin)
        CharacterInfoDetail(Strings.CHARACTER_DETAIL_GENDER, characterModel.gender)
    }
}

@Composable
fun CharacterInfoDetail(title: String, detail: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(DETAIL_ARRANGEMENT_SPACING)
    ) {
        Text(
            text = title,
            color = DefaultTextColor,
            style = AppTypography.regularBoldXS
        )

        Text(
            text = detail,
            color = Green,
            style = AppTypography.regularXS
        )
    }
}