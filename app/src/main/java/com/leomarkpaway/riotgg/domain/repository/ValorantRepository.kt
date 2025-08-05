package com.leomarkpaway.riotgg.domain.repository

import com.leomarkpaway.riotgg.domain.entity.valorant.AgentEntity
import com.skydoves.sandwich.ApiResponse

interface ValorantRepository {
    suspend fun getAllAgents(): ApiResponse<AgentEntity>
    suspend fun getAgentDetails(uuid: String): ApiResponse<AgentEntity>
}