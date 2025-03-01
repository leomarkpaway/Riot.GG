package com.leomarkpaway.riotgg.di


import com.leomarkpaway.riotgg.data.remote.RiotApiService
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.BASE_URL
import com.leomarkpaway.riotgg.data.repository.RepositoryImpl
import com.leomarkpaway.riotgg.domain.repository.Repository
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionListUsaCase
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListViewModel
import com.leomarkpaway.riotgg.common.util.navigator.DefaultNavigator
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.domain.usecase.FilterChampionListUseCase
import com.leomarkpaway.riotgg.presentation.main.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Network
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

    // Repository
    single<RiotApiService> { RiotApiService(httpClient = get()) }
    single<Repository> { RepositoryImpl(riotApiService = get()) }

    // Navigator
    single<Navigator> { DefaultNavigator(startDestination = Destination.LeagueOfLegends) }

    // UseCase
    factory<FetchChampionListUsaCase> { FetchChampionListUsaCase(repository = get()) }
    factory<FetchChampionDetailsUseCase> { FetchChampionDetailsUseCase(repository = get()) }
    factory<FilterChampionListUseCase> { FilterChampionListUseCase() }

    // ViewModel
    viewModel<ChampionListViewModel> {
        ChampionListViewModel(
            navigator = get(),
            fetchChampionListUseCase = get(),
            filterChampionListUseCase = get()
        )
    }
    viewModel<ChampionDetailsViewModel> {
        ChampionDetailsViewModel(
            fetchChampionDetailsUseCase = get()
        )
    }
    viewModel<MainViewModel> { MainViewModel(navigator = get()) }
}