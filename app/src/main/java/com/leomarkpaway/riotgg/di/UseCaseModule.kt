package com.leomarkpaway.riotgg.di


import com.leomarkpaway.riotgg.domain.usecase.FetchChampionDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionListUsaCase
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FetchChampionListUsaCase> { FetchChampionListUsaCase(repository = get()) }
    viewModel<ChampionListViewModel> { ChampionListViewModel(fetchChampionListUseCase = get()) }
    factory<FetchChampionDetailsUseCase> { FetchChampionDetailsUseCase(repository = get()) }
    viewModel<ChampionDetailsViewModel> { ChampionDetailsViewModel(fetchChampionDetailsUseCase = get()) }
}