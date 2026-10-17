package com.angiedev.rickmortyapp.fakes

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/** A [Clock] that only moves when the test moves it. */
class TestClock(var current: Instant) : Clock {
    override fun now(): Instant = current
}
