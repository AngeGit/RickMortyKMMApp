package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.domain.GetAllCharactersUseCase
import com.angiedev.rickmortyapp.domain.GetAllEpisodesUseCase
import com.angiedev.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import com.angiedev.rickmortyapp.domain.GetEpisodesUseCase
import kotlinx.datetime.Clock
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import kotlin.random.Random

val domainModule = module {
    single<Clock> { Clock.System }
    single<Random> { Random.Default }

    factoryOf(::GetCharacterOfTheDayUseCase)
    factoryOf(::GetAllCharactersUseCase)
    factoryOf(::GetAllEpisodesUseCase)
    factoryOf(::GetEpisodesUseCase)
}