package com.leomarkpaway.riotgg.presentation.champion_list

import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel

sealed class ChampionListSideEffect {
    data class OnError(val message: String) : ChampionListSideEffect()
    data class OnSearch(val champions: List<ChampionModel>) : ChampionListSideEffect()
}