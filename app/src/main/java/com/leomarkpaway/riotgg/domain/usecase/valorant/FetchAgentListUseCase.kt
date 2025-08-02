package com.leomarkpaway.riotgg.domain.usecase.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.model.AgentModel
import com.leomarkpaway.riotgg.domain.entity.valorant.toAgentList
import com.leomarkpaway.riotgg.domain.repository.ValorantRepository
import com.skydoves.sandwich.onSuccess

class FetchAgentListUseCase(private val valorantRepository: ValorantRepository) {
    suspend operator fun invoke() : List<AgentModel> {
        var championList: List<AgentModel> = emptyList()
        valorantRepository.getAllAgents().onSuccess {
            championList = data.agents.toAgentList()
        }
        return championList
    }
}