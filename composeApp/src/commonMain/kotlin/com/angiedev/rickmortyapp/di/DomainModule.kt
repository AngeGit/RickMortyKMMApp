package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import com.angiedev.rickmortyapp.domain.GetEpisodesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCharacterOfTheDayUseCase)
    factoryOf(::GetAllCharactersUseCase)
    factoryOf(::GetAllEpisodesUseCase)
    factoryOf(::GetEpisodesUseCase)
}