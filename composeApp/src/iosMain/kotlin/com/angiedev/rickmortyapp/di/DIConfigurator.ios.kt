package com.angiedev.rickmortyapp.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        // Here we can provide platform specific IOS modules
    }
}