package com.leomarkpaway.riotgg.presentation.main.component.drawer

import com.leomarkpaway.riotgg.R
import com.leomarkpaway.riotgg.common.util.navigator.Destination

enum class DrawerItemModel(
    val title: String,
    val icon: Int? = 0,
    val destination: Destination
) {
    LeagueOfLegends(
        icon = R.drawable.lol_logomark_black,
        title = "League of Legends",
        destination = Destination.LeagueOfLegends
    ),
    TeamfightTactics(
        icon = R.drawable.tft_logomark_black,
        title = "Teamfight Tactics",
        destination = Destination.TeamfightTactics
    ),
    Valorant(
        icon = R.drawable.v_logomark_black,
        title = "Valorant",
        destination = Destination.Valorant
    ),
    Settings(
        title = "Settings",
        destination = Destination.Settings
    )
}