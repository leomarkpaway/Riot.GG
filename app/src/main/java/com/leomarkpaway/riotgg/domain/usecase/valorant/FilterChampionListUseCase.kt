package com.leomarkpaway.riotgg.domain.usecase.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.model.AgentModel

class FilterAgentListUseCase {
    suspend operator fun invoke(agentName: String, agentList: List<AgentModel>) : List<AgentModel> {
        return agentList.filter { it.developerName.contains(agentName, ignoreCase = true) }
    }
}