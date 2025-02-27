package com.leomarkpaway.riotgg.di

import com.leomarkpaway.riotgg.data.remote.RiotApiService
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(OkHttp.create()) {
            defaultRequest {
                url(BASE_URL)
                header(HttpHeaders.ContentType, "application/json")
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    single { RiotApiService(httpClient = get()) }
}