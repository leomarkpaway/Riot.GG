package com.leomarkpaway.riotgg.presentation.champion_list

import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel

data class ChampionListState(
    val isOnLoading: Boolean = true,
    val searchText: String = "",
    val champions: List<ChampionModel> = emptyList(),
    val filteredChampions: List<ChampionModel> = emptyList()
)
