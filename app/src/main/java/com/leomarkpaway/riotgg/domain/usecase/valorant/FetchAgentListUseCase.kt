package com.leomarkpaway.riotgg.domain.usecase.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.model.AgentModel
import com.leomarkpaway.riotgg.domain.repository.ValorantRepository
import com.skydoves.sandwich.onSuccess

class FetchAgentListUseCase(private val valorantRepository: ValorantRepository) {
    suspend operator fun invoke() : List<AgentModel> {
        var agentList: List<AgentModel> = emptyList()
        val response = valorantRepository.getAllAgents()
        response.onSuccess { agentList = data.agents }
        val filteredAgents = agentList.filter { it.fullPortrait != null }
        return filteredAgents
    }
}