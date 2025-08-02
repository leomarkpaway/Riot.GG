package com.leomarkpaway.riotgg.domain.entity.valorant.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityModel(
    @SerialName("description")
    val description: String,
    @SerialName("displayIcon")
    val displayIcon: String? = null,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("slot")
    val slot: String
)