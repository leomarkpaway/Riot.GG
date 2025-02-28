package com.leomarkpaway.riotgg.presentation.main.component

enum class MainDrawerState {
    Opened,
    Closed
}

fun MainDrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun MainDrawerState.opposite(): MainDrawerState {
    return if (this == MainDrawerState.Opened) MainDrawerState.Closed
    else MainDrawerState.Opened
}