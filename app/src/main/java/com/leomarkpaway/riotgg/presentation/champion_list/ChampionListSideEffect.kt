package com.leomarkpaway.riotgg.presentation.champion_list

sealed class ChampionListSideEffect {
    data class OnError(val message: String) : ChampionListSideEffect()
}