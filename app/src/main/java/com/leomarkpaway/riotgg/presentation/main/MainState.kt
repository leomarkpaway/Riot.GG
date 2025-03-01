package com.leomarkpaway.riotgg.presentation.main

import com.leomarkpaway.riotgg.presentation.main.component.drawer.DrawerItemModel

data class MainState(
    val isDrawerOpen: Boolean = false,
    val selectedDrawerItem: DrawerItemModel = DrawerItemModel.LeagueOfLegends
)
