package com.leomarkpaway.riotgg.common.extension

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

fun OkHttp.createNetwork(baseUrl: String, loggerTag: String) {
    HttpClient(this.create()) {
        defaultRequest {
            url(baseUrl)
            header(HttpHeaders.ContentType, "application/json")
            header(HttpHeaders.Accept, "application/json, text/plain")
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.tag("${loggerTag}_Http").d(message)
                }
            }
            level = LogLevel.ALL
        }
    }
}