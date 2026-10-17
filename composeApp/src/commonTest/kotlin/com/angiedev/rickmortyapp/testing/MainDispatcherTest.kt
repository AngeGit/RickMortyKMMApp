package com.angiedev.rickmortyapp.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
 * Base class for ViewModel tests.
 *
 * `viewModelScope` runs on `Dispatchers.Main`, which does not exist in unit tests. This swaps it for
 * a [StandardTestDispatcher]; `runTest` picks up the same scheduler, so virtual time is shared.
 *
 * With a standard dispatcher, the coroutines a ViewModel launches in `init` only run when the test
 * suspends (for example in Turbine's `awaitItem()`) or calls `advanceUntilIdle()`.
 */
@OptIn(ExperimentalCoroutinesApi::class)
abstract class MainDispatcherTest {

    protected val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setMainDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun resetMainDispatcher() {
        Dispatchers.resetMain()
    }
}
