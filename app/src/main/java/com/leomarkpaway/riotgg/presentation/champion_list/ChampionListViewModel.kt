package com.leomarkpaway.riotgg.presentation.champion_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionListUsaCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ChampionListViewModel(
    private val fetchChampionListUseCase: FetchChampionListUsaCase,
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
            postSideEffect(ChampionListSideEffect.OnSearch(state.champions.filter {
                it.name?.contains(text, ignoreCase = true) == true
            }))
        }
    }

    fun onClickItem(championName: String) {
        TODO("Not yet implemented")
    }

}