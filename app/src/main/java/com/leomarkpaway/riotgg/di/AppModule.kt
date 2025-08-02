package com.leomarkpaway.riotgg.di

import com.leomarkpaway.riotgg.data.remote.LeagueOfLegendsApiService
import com.leomarkpaway.riotgg.data.remote.LeagueOfLegendsApiService.Companion.BASE_URL
import com.leomarkpaway.riotgg.data.repository.LeagueOfLegendsRepositoryImpl
import com.leomarkpaway.riotgg.domain.repository.LeagueOfLegendsRepository
import com.leomarkpaway.riotgg.domain.usecase.league_of_legends.FetchChampionDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.league_of_legends.FetchChampionListUsaCase
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list.ChampionListViewModel
import com.leomarkpaway.riotgg.common.util.navigator.DefaultNavigator
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.domain.usecase.league_of_legends.FilterChampionListUseCase
import com.leomarkpaway.riotgg.presentation.main.MainViewModel
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
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import timber.log.Timber

val appModule = module {
    // Navigator
    single<Navigator> { DefaultNavigator(startDestination = Destination.LeagueOfLegends) }
    // Main ViewModel
    viewModel<MainViewModel> { MainViewModel(navigator = get()) }
    // League of Legends Network
    single {
        HttpClient(OkHttp.create()) {
            defaultRequest {
                url(BASE_URL)
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
                        Timber.tag("League_of_Legends_Http").d(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
    // League of Legends Repositories
    single<LeagueOfLegendsApiService> { LeagueOfLegendsApiService(httpClient = get()) }
    single<LeagueOfLegendsRepository> { LeagueOfLegendsRepositoryImpl(leagueOfLegendsApiService = get()) }
    // League of Legends UseCases
    factory<FetchChampionListUsaCase> { FetchChampionListUsaCase(leagueOfLegendsRepository = get()) }
    factory<FetchChampionDetailsUseCase> { FetchChampionDetailsUseCase(leagueOfLegendsRepository = get()) }
    factory<FilterChampionListUseCase> { FilterChampionListUseCase() }
    // League of Legends ViewModels
    viewModel<ChampionListViewModel> {
        ChampionListViewModel(
            navigator = get(),
            fetchChampionListUseCase = get(),
            filterChampionListUseCase = get()
        )
    }
    viewModel<ChampionDetailsViewModel> {
        ChampionDetailsViewModel(fetchChampionDetailsUseCase = get())
    }
}