package com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list

sealed class ChampionListSideEffect {
    data class OnError(val message: String) : ChampionListSideEffect()
}