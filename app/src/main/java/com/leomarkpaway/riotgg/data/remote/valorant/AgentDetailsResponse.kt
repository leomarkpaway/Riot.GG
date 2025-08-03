package com.leomarkpaway.riotgg.data.remote.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.AgentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentDetailsResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("data")
    val agents: AgentModel = AgentModel()
)

