package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCharacterOfTheDayUseCase)
    factoryOf(::GetAllCharactersUseCase)
}