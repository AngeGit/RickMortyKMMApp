package com.angiedev.rickmortyapp.di

import com.angiedev.rickmortyapp.data.remote.ApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                    //I'm not using api_key, but you can add it here
                    //parameters.append("api_key", "123456")
                }
            }
        }
    }

    factoryOf(::ApiService)
}