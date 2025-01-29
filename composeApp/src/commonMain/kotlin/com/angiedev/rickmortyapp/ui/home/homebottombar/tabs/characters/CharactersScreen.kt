package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen() {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state = charactersViewModel.characterOfTheDayState.collectAsStateWithLifecycle()
    val characters = state.value.characters.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        CharactersGridList(characters,state)
    }
}


/*
    * The collectAsState() function is the original way to collect a Flow or StateFlow inside a composable.
    * It allows you to convert the flow’s emitted values into Compose’s State type, which makes the values
    * reactive and recomposable.
    *       -No Built-In Lifecycle Awareness: The key thing to remember about collectAsState() is that it isn’t
    *       lifecycle-aware by default. It will collect the flow as long as the composable is active, even when
    *       it might not be visible on the screen. This can lead to unnecessary resource consumption if the composable
    *       is inactive, but still collecting values.
    *       -Resource-Intensive in Inactive States: Since collectAsState() doesn’t stop collecting when the composable
    *       is not on screen or in a paused state, it can potentially keep observing data and trigger recompositions,
    *       even when the UI is not visible. In many cases, this can result in inefficient use of resources,
    *       such as power and CPU.
    *       -Use Case: collectAsState() works well when the composable is short-lived or always visible.
    *       But when dealing with composables that can go in and out of view (e.g., in a LazyColumn or when
    *       navigating between screens), collectAsState() may not be the most efficient choice.
    *
    * collectAsStateWithLifecycle()
    * Recognizing the need for lifecycle-aware flow collection, Android introduced collectAsStateWithLifecycle().
    * This is an enhanced version of collectAsState() that integrates directly with the lifecycle of a composable.
    *       -Lifecycle Awareness: The most significant difference is that collectAsStateWithLifecycle() is lifecycle-aware.
    *       This means that it will start collecting the Flow only when the composable is in a STARTED state or higher
    *       (i.e., when the composable is visible and active). It automatically stops collecting when the composable is
    *       paused or stopped. This makes it more efficient than collectAsState() when it comes to handling resources
    *       during state transitions.
    *       -Automatic Pausing and Resuming: When the composable goes into the background (e.g., the user navigates away
    *       from the screen or the app is minimized), collectAsStateWithLifecycle() stops collecting the Flow, preventing
    *       unnecessary recompositions and resource use. Once the composable is active again, it resumes collection.
    *       -Less Resource Overhead: By pausing collection during inactive states, this function reduces the strain on
    *       system resources, like CPU and battery, especially in complex UIs with multiple state observers.
    *       -Consistency with ViewModel Lifecycles: Since many apps use ViewModels, which themselves are lifecycle-aware,
    *       combining collectAsStateWithLifecycle() with ViewModels creates a cohesive flow of state management that aligns
    *       perfectly with Android’s lifecycle components.
    *       -Use Case: collectAsStateWithLifecycle() is the better choice when your composable needs to observe a Flow
    *       that should pause and resume based on the lifecycle state of the composable or the app. It is particularly
    *       useful in screens that the user navigates away from or in UIs that are dynamically created and destroyed,
    *       like lists or paginated content.
    *
    * SO... WHEN I SHOULD USE collectAsState() AND WHEN collectAsStateWithLifecycle()?
    *
    * Use collectAsState() when:
    *   -The composable is short-lived or unlikely to be paused/stopped.
    *   -You don’t care about lifecycle-awareness, and resource consumption is not a concern (e.g., simple static screens).
    *   -The Flow needs to be collected continuously regardless of UI state.
    *
    * Use collectAsStateWithLifecycle() when:
    *   -You want automatic handling of lifecycle states for resource efficiency.
    *   -The composable is part of a dynamic or multi-screen app, where the user navigates between different screens.
    *   -You want to ensure that data collection only happens when the composable is active, and the UI is visible.
    *   -You’re optimizing for performance, especially in battery- or resource-sensitive applications.
    * */
