package com.leomarkpaway.riotgg.data.remote

import com.leomarkpaway.riotgg.domain.entity.valorant.AgentEntity
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient

class ValorantApiService(private val httpClient: HttpClient) {

    suspend fun getAllAgents() : ApiResponse<AgentEntity> {
        return httpClient.getApiResponse("agents")
    }

    suspend fun getAgentDetails(uuid: String) : ApiResponse<AgentEntity> {
        return httpClient.getApiResponse("agents/$uuid")
    }

    companion object {
        private const val VERSION = "v1"
        const val VAL_BASE_URL = "https://valorant-api.com/$VERSION/"
    }
}