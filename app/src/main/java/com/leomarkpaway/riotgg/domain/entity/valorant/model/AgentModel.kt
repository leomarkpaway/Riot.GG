package com.leomarkpaway.riotgg.domain.entity.valorant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentModel(
    @SerialName("abilities")
    val abilities: List<AbilityModel> = emptyList(),
    @SerialName("assetPath")
    val assetPath: String = "",
    @SerialName("background")
    val background: String = "",
    @SerialName("backgroundGradientColors")
    val backgroundGradientColors: List<String> = emptyList(),
    @SerialName("bustPortrait")
    val bustPortrait: String = "",
    @SerialName("characterTags")
    val characterTags: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("developerName")
    val developerName: String = "",
    @SerialName("displayIcon")
    val displayIcon: String = "",
    @SerialName("displayIconSmall")
    val displayIconSmall: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("fullPortrait")
    val fullPortrait: String = "",
    @SerialName("fullPortraitV2")
    val fullPortraitV2: String = "",
    @SerialName("isAvailableForTest")
    val isAvailableForTest: Boolean = false,
    @SerialName("isBaseContent")
    val isBaseContent: Boolean = false,
    @SerialName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean = false,
    @SerialName("isPlayableCharacter")
    val isPlayableCharacter: Boolean = false,
    @SerialName("killfeedPortrait")
    val killFeedPortrait: String = "",
    @SerialName("recruitmentData")
    val recruitmentData: String = "",
    @SerialName("releaseDate")
    val releaseDate: String = "",
    @SerialName("role")
    val role: RoleModel = RoleModel(),
    @SerialName("uuid")
    val uuid: String = "",
    @SerialName("voiceLine")
    val voiceLine: String = ""
)