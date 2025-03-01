package com.leomarkpaway.riotgg.presentation.main

import androidx.lifecycle.ViewModel
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.presentation.main.component.drawer.DrawerItemModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(private val navigator: Navigator) : ContainerHost<MainState, Nothing>, ViewModel() {
    override val container = container<MainState, Nothing>(MainState())

    fun updateIsDrawerOpen(isDrawerOpen: Boolean) {
        intent {
            reduce { state.copy(isDrawerOpen = isDrawerOpen) }
        }
    }

    fun updateSelectedDrawerItem(selectedDrawerItem: DrawerItemModel) {
        intent {
            reduce { state.copy(selectedDrawerItem = selectedDrawerItem) }
            navigator.navigate(state.selectedDrawerItem.destination)
        }
    }

}