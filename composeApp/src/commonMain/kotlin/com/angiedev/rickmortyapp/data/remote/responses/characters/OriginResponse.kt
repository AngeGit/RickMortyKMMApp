package com.angiedev.rickmortyapp.data.remote.responses.characters

import kotlinx.serialization.Serializable

@Serializable
data class OriginResponse(
    val name: String,
    val url: String
)
