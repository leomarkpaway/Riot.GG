package com.leomarkpaway.riotgg.domain.entity.valorant

import com.leomarkpaway.riotgg.domain.entity.valorant.model.AgentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentEntity(
    @SerialName("status")
    val status: Int,
    @SerialName("data")
    val agents: Map<String, AgentModel> = emptyMap()
)

fun Map<String, AgentModel>.toAgentList(): List<AgentModel> = this.values.toList()