package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.ui.detail.CharacterDetailViewModel
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters.CharactersViewModel
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}