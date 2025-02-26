package com.leomarkpaway.riotgg.presentation.champion_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ChampionDetailsViewModel(
    private val fetchChampionDetailsUseCase: FetchChampionDetailsUseCase
) : ContainerHost<ChampionDetailsState, Nothing>,ViewModel() {
    override val container = container<ChampionDetailsState, Nothing>(ChampionDetailsState())

    fun fetchChampionDetails(championName: String) {
        intent {
            viewModelScope.launch(Dispatchers.IO) {
                val championDetails = fetchChampionDetailsUseCase.invoke(championName)
                reduce { state.copy(champion = championDetails) }
            }
        }
    }

}