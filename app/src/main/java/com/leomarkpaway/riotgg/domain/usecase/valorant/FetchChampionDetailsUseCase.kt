package com.leomarkpaway.riotgg.domain.usecase.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.model.AgentModel
import com.leomarkpaway.riotgg.domain.repository.ValorantRepository
import com.skydoves.sandwich.onSuccess

class FetchAgentDetailsUseCase(private val valorantRepository: ValorantRepository) {
    suspend operator fun invoke(uuid: String) : AgentModel? {
        var agentDetails: AgentModel? = AgentModel()
        valorantRepository.getAgentDetails(uuid).onSuccess {
            agentDetails = data.agents.values.firstOrNull()
        }
        return agentDetails
    }
}