package com.leomarkpaway.riotgg.presentation.valorant.agent_list

import com.leomarkpaway.riotgg.domain.entity.valorant.AgentModel

data class AgentListState(
    val isOnLoading: Boolean = true,
    val searchText: String = "",
    val agents: List<AgentModel> = emptyList(),
    val filteredAgents: List<AgentModel> = emptyList()
)
