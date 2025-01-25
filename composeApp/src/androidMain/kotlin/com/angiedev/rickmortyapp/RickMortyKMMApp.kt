package com.angiedev.rickmortyapp

import android.app.Application
import com.angiedev.rickmortyapp.di.initKoin

class RickMortyKMMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}