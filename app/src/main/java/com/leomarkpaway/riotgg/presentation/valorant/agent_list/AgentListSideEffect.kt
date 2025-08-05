package com.leomarkpaway.riotgg.presentation.valorant.agent_list

sealed class AgentListSideEffect {
    data class OnError(val message: String) : AgentListSideEffect()
}