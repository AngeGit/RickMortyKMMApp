package com.angiedev.rickmortyapp.data.remote.responses

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) {
}