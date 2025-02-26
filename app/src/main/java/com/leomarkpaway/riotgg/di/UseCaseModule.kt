package com.leomarkpaway.riotgg.di


import com.leomarkpaway.riotgg.domain.usecase.FetchChampionDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionListUsaCase
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListViewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<FetchChampionListUsaCase> { FetchChampionListUsaCase(repository = get()) }
    single<ChampionListViewModel> { ChampionListViewModel(fetchChampionListUseCase = get()) }
    single<FetchChampionDetailsUseCase> { FetchChampionDetailsUseCase(repository = get()) }
    single<ChampionDetailsViewModel> { ChampionDetailsViewModel(fetchChampionDetailsUseCase = get()) }
}