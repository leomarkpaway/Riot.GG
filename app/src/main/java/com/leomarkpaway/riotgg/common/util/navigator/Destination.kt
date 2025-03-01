package com.leomarkpaway.riotgg.common.util.navigator

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object LeagueOfLegends: Destination
    @Serializable
    data class ChampionDetails(val championName: String): Destination
    @Serializable
    data object TeamfightTactics: Destination
    @Serializable
    data object Valorant: Destination
    @Serializable
    data object Settings: Destination
}