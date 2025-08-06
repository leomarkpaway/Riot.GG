package com.leomarkpaway.riotgg.domain.repository

import com.leomarkpaway.riotgg.data.remote.valorant.AgentListResponse
import com.leomarkpaway.riotgg.data.remote.valorant.AgentDetailsResponse
import com.skydoves.sandwich.ApiResponse

interface ValorantRepository {
    suspend fun getAllAgents(): ApiResponse<AgentListResponse>
    suspend fun getAgentDetails(uuid: String): ApiResponse<AgentDetailsResponse>
}