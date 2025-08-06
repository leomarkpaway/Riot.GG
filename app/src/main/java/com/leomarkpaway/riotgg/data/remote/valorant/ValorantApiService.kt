package com.leomarkpaway.riotgg.data.remote.valorant

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient

class ValorantApiService(private val httpClient: HttpClient) {

    suspend fun getAllAgents() : ApiResponse<AgentListResponse> {
        return httpClient.getApiResponse("agents")
    }
    suspend fun getAgentDetails(uuid: String) : ApiResponse<AgentDetailsResponse> {
        return httpClient.getApiResponse("agents/$uuid")
    }

    companion object {
        private const val VERSION = "v1"
        const val VAL_BASE_URL = "https://valorant-api.com/$VERSION/"
    }
}