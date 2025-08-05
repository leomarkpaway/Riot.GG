package com.leomarkpaway.riotgg.di

import com.leomarkpaway.riotgg.common.extension.createNetwork
import com.leomarkpaway.riotgg.common.util.navigator.DefaultNavigator
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.data.remote.LeagueOfLegendsApiService
import com.leomarkpaway.riotgg.data.remote.LeagueOfLegendsApiService.Companion.LOL_BASE_URL
import com.leomarkpaway.riotgg.data.remote.ValorantApiService
import com.leomarkpaway.riotgg.data.remote.ValorantApiService.Companion.VAL_BASE_URL
import com.leomarkpaway.riotgg.data.repository.LeagueOfLegendsRepositoryImpl
import com.leomarkpaway.riotgg.data.repository.ValorantRepositoryImpl
import com.leomarkpaway.riotgg.domain.repository.LeagueOfLegendsRepository
import com.leomarkpaway.riotgg.domain.repository.ValorantRepository
import com.leomarkpaway.riotgg.domain.usecase.league_of_legends.FetchChampionDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.league_of_legends.FetchChampionListUsaCase
import com.leomarkpaway.riotgg.domain.usecase.league_of_legends.FilterChampionListUseCase
import com.leomarkpaway.riotgg.domain.usecase.valorant.FetchAgentDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.valorant.FetchAgentListUseCase
import com.leomarkpaway.riotgg.domain.usecase.valorant.FilterAgentListUseCase
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list.ChampionListViewModel
import com.leomarkpaway.riotgg.presentation.main.MainViewModel
import com.leomarkpaway.riotgg.presentation.valorant.agent_list.AgentListViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    // Navigator
    single<Navigator> { DefaultNavigator(startDestination = Destination.LeagueOfLegends) }
    // Main ViewModel
    viewModel<MainViewModel> { MainViewModel(navigator = get()) }

    // League of Legends Network
    single(named("League_of_Legends")) {
        OkHttp.createNetwork(baseUrl = LOL_BASE_URL, loggerTag = "LOL")
    }
    // League of Legends Repository
    single<LeagueOfLegendsApiService> { LeagueOfLegendsApiService(httpClient = get(named("League_of_Legends"))) }
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

    // Valorant Network
    single(named("Valorant")) {
        OkHttp.createNetwork(baseUrl = VAL_BASE_URL, loggerTag = "VAL")
    }
    // Valorant Repository
    single<ValorantApiService> { ValorantApiService(httpClient = get(named("Valorant"))) }
    single<ValorantRepository> { ValorantRepositoryImpl(valorantApiService = get()) }
    // Valorant UseCases
    factory<FetchAgentListUseCase> { FetchAgentListUseCase(valorantRepository = get()) }
    factory<FetchAgentDetailsUseCase> { FetchAgentDetailsUseCase(valorantRepository = get()) }
    factory<FilterAgentListUseCase> { FilterAgentListUseCase() }
    // Valorant ViewModel
    viewModel<AgentListViewModel>{
        AgentListViewModel(
            navigator = get(),
            fetchAgentListUseCase = get(),
            filterAgentListUseCase = get()
        )
    }
}