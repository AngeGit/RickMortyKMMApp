package com.angiedev.rickmortyapp.ui.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.domain.model.EpisodeModel
import com.angiedev.rickmortyapp.ui.core.resources.BackgroundSecondaryColor
import com.angiedev.rickmortyapp.ui.core.resources.primaryWhite
import com.angiedev.rickmortyapp.ui.detail.CharacterDetailViewModel
import com.angiedev.rickmortyapp.ui.detail.screen.components.CHARACTER_CARD_ROUNDED_CORNER
import com.angiedev.rickmortyapp.ui.detail.screen.components.CharacterEpisodesList
import com.angiedev.rickmortyapp.ui.detail.screen.components.CharacterInfo
import com.angiedev.rickmortyapp.ui.detail.screen.components.MainHeader
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parameterSetOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(
    characterModel: CharacterModel
) {
    val characterDetailViewModel =
        koinViewModel<CharacterDetailViewModel>(parameters = { parameterSetOf(characterModel) })

    val state by characterDetailViewModel.uiState.collectAsStateWithLifecycle()

    val mainModifier = Modifier.fillMaxSize().background(color = primaryWhite)
    val bodyModifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxWidth()

    Column(
        modifier = mainModifier
    ) {
        MainHeader(state.characterModel)
        Column(
            modifier = mainModifier
                .clip(RoundedCornerShape(topStartPercent = 5, topEndPercent = 5))
                .background(color = BackgroundSecondaryColor)
        ) {
            CharacterInfo(
                state.characterModel,
                bodyModifier.padding(horizontal = 16.dp).padding(top = 20.dp)
            )
            CharacterEpisodesList(
                state.episodeList,
                bodyModifier.padding(horizontal = 16.dp, vertical = 20.dp).padding(bottom = 20.dp)
            )
        }
    }
}
