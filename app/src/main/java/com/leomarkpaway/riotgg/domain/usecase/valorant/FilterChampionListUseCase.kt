package com.leomarkpaway.riotgg.domain.usecase.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.AgentModel

class FilterAgentListUseCase {
    suspend operator fun invoke(agentName: String, agentList: List<AgentModel>) : List<AgentModel> {
        return agentList.filter { it.displayName.contains(agentName, ignoreCase = true) }
    }
}