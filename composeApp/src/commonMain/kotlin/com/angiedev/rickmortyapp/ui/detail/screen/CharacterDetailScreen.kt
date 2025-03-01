package com.angiedev.rickmortyapp.ui.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.detail.CharacterDetailViewModel
import com.angiedev.rickmortyapp.ui.detail.screen.components.CharacterEpisodesList
import com.angiedev.rickmortyapp.ui.detail.screen.components.CharacterInfo
import com.angiedev.rickmortyapp.ui.detail.screen.components.CustomSpacer
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

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        val bodyModifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        MainHeader(state.characterModel)
        CharacterInfo(state.characterModel, bodyModifier)
        CustomSpacer()
        CharacterEpisodesList(state.episodeList, bodyModifier)
    }
}
