package com.angiedev.rickmortyapp.fakes

import com.angiedev.rickmortyapp.data.remote.ApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * A real [ApiService] on top of Ktor's [MockEngine], so requests never leave the test.
 *
 * Answers every paged request with [pageJson] for the requested page and records the pages it was
 * asked for. Set [error] to make every request fail.
 */
class FakeApi(private val pageJson: (page: Int) -> String) {

    val requestedPages = mutableListOf<Int>()
    var error: Throwable? = null

    private val engine = MockEngine { request ->
        error?.let { throw it }
        val page = request.url.parameters["page"]!!.toInt()
        requestedPages += page
        respond(
            content = pageJson(page),
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
        )
    }

    // Same JSON setup as dataModule
    val service = ApiService(
        HttpClient(engine) {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    )
}
