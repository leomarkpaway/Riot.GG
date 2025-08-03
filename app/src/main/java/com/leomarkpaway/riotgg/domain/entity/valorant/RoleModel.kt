package com.leomarkpaway.riotgg.domain.entity.valorant


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoleModel(
    @SerialName("assetPath")
    val assetPath: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("displayIcon")
    val displayIcon: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("uuid")
    val uuid: String = ""
)