package com.leomarkpaway.riotgg.presentation.main.component

import com.leomarkpaway.riotgg.R

enum class DrawerItemModel(
    val title: String,
    val icon: Int
) {
    LeagueOfLegends(
        icon = R.drawable.ic_launcher_foreground,
        title = "League of Legends"
    ),
    Settings(
        icon = R.drawable.ic_launcher_foreground,
        title = "Settings"
    )
}