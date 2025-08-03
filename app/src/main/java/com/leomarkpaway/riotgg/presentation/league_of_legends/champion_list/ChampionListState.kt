package com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.ChampionModel

data class ChampionListState(
    val isOnLoading: Boolean = true,
    val searchText: String = "",
    val champions: List<ChampionModel> = emptyList(),
    val filteredChampions: List<ChampionModel> = emptyList()
)
