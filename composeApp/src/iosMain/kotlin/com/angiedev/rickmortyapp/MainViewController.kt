package com.angiedev.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.angiedev.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }