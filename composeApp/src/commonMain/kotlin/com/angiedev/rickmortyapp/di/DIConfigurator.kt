package com.angiedev.rickmortyapp.di

import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(
            dataModule,
            domainModule,
            uiModule
        )
    }
}