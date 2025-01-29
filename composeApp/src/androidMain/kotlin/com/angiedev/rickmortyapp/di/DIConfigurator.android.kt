package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.data.local.RickMortyDatabase
import com.angiedev.rickmortyapp.data.local.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    // Add platform Android specific dependencies here
    single<RickMortyDatabase> { getDatabase(get()) }
}