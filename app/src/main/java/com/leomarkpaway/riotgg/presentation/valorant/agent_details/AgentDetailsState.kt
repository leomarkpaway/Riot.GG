package com.leomarkpaway.riotgg.presentation.valorant.agent_details

import com.leomarkpaway.riotgg.domain.entity.valorant.AgentModel

data class AgentDetailsState(
    val isOnLoading: Boolean = true,
    val agent: AgentModel? = null
)