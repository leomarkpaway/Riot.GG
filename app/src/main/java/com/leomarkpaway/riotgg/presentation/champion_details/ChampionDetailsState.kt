package com.leomarkpaway.riotgg.presentation.champion_details

import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel

data class ChampionDetailsState(
    val isOnLoading: Boolean = true,
    val champion: ChampionModel? = null,
    val generalStats: List<String> = emptyList(),
    val skins: ArrayList<Pair<String, Int>> = arrayListOf()
)