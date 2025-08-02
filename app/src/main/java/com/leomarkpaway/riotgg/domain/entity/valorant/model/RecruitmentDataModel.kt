package com.leomarkpaway.riotgg.domain.entity.valorant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentDataModel(
    @SerialName("counterId")
    val counterId: String,
    @SerialName("endDate")
    val endDate: String,
    @SerialName("levelVpCostOverride")
    val levelVpCostOverride: Int,
    @SerialName("milestoneId")
    val milestoneId: String,
    @SerialName("milestoneThreshold")
    val milestoneThreshold: Int,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("useLevelVpCostOverride")
    val useLevelVpCostOverride: Boolean
)