package com.leomarkpaway.riotgg.di

import com.leomarkpaway.riotgg.data.remote.RiotApiService
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
        val baseUrl = "https://ddragon.leagueoflegends.com/cdn"
        HttpClient(OkHttp.create()) {
            defaultRequest {
                url(baseUrl)
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