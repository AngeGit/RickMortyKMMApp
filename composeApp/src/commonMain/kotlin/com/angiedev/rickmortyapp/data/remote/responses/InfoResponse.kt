package com.angiedev.rickmortyapp.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) {
}