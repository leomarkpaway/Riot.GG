package com.leomarkpaway.riotgg.domain.entity.league_of_legends.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkinModel(
    @SerialName("num") var skinNumber: Int? = null,
    @SerialName("name") var skinName: String? = null,
)