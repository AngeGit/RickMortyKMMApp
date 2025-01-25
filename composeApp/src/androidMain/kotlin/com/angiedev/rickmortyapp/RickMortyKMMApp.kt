package com.angiedev.rickmortyapp

import android.app.Application
import com.angiedev.rickmortyapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickMortyKMMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@RickMortyKMMApp) //for injecting android context (application context)
        }
    }
}