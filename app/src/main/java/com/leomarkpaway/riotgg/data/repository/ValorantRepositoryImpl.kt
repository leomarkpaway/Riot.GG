package com.leomarkpaway.riotgg.data.repository

import com.leomarkpaway.riotgg.data.remote.valorant.ValorantApiService
import com.leomarkpaway.riotgg.domain.repository.ValorantRepository

class ValorantRepositoryImpl(
    private val valorantApiService: ValorantApiService
) : ValorantRepository {
    override suspend fun getAllAgents() = valorantApiService.getAllAgents()
    override suspend fun getAgentDetails(uuid: String)= valorantApiService.getAgentDetails(uuid)
}