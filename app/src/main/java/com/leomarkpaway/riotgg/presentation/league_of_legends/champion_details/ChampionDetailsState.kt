package com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.model.ChampionModel

data class ChampionDetailsState(
    val isOnLoading: Boolean = true,
    val champion: ChampionModel? = null,
    val generalStats: List<String> = emptyList(),
    val skins: ArrayList<Pair<String, Int>> = arrayListOf()
)