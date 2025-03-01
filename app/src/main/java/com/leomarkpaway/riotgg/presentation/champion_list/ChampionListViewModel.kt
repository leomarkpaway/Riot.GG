package com.leomarkpaway.riotgg.presentation.champion_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionListUsaCase
import com.leomarkpaway.riotgg.domain.usecase.FilterChampionListUseCase
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ChampionListViewModel(
    private val navigator: Navigator,
    private val fetchChampionListUseCase: FetchChampionListUsaCase,
    private val filterChampionListUseCase: FilterChampionListUseCase,
) : ContainerHost<ChampionListState, ChampionListSideEffect>, ViewModel() {
    override val container = container<ChampionListState, ChampionListSideEffect>(ChampionListState())

    init {
        intent {
            viewModelScope.launch {
                val championList = fetchChampionListUseCase.invoke()
                reduce { state.copy(champions = championList) }
                delay(500)
                reduce { state.copy(isOnLoading = false) }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        intent {
            reduce { state.copy(searchText = text) }
            viewModelScope.launch(Dispatchers.IO) {
                val filteredChampions = filterChampionListUseCase.invoke(text, state.champions)
                reduce { state.copy(filteredChampions = filteredChampions) }
            }
        }
    }

    fun onClickItem(championName: String) {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.ChampionDetails(championName),
                navOptions = {
                    popUpTo(Destination.LeagueOfLegends) {
                        inclusive = false
                    }
                }
            )
        }
    }

}