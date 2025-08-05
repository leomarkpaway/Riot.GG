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
    val background: String? = null,
    @SerialName("backgroundGradientColors")
    val backgroundGradientColors: List<String> = emptyList(),
    @SerialName("bustPortrait")
    val bustPortrait: String? = null,
    @SerialName("characterTags")
    val characterTags: List<String>? = null,
    @SerialName("description")
    val description: String = "",
    @SerialName("developerName")
    val developerName: String = "",
    @SerialName("displayIcon")
    val displayIcon: String? = null,
    @SerialName("displayIconSmall")
    val displayIconSmall: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("fullPortrait")
    val fullPortrait: String? = null,
    @SerialName("fullPortraitV2")
    val fullPortraitV2: String? = null,
    @SerialName("isAvailableForTest")
    val isAvailableForTest: Boolean = false,
    @SerialName("isBaseContent")
    val isBaseContent: Boolean = false,
    @SerialName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean = false,
    @SerialName("isPlayableCharacter")
    val isPlayableCharacter: Boolean = false,
    @SerialName("killfeedPortrait")
    val killFeedPortrait: String? = null,
    @SerialName("recruitmentData")
    val recruitmentData: RecruitmentDataModel? = null,
    @SerialName("releaseDate")
    val releaseDate: String = "",
    @SerialName("role")
    val role: RoleModel? = null,
    @SerialName("uuid")
    val uuid: String = "",
    @SerialName("voiceLine")
    val voiceLine: String? = null
)