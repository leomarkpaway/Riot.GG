package com.leomarkpaway.riotgg.common.util.navigator

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Home: Destination
    @Serializable
    data object ChampionList: Destination
    @Serializable
    data class ChampionDetails(val championName: String): Destination
}