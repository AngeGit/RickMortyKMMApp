package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.data.local.RickMortyDatabase
import com.angiedev.rickmortyapp.data.local.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        // Here we can provide platform specific IOS modules
        single<RickMortyDatabase> { getDatabase() }
    }
}