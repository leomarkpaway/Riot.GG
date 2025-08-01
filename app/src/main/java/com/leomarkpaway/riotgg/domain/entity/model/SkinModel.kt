package com.leomarkpaway.riotgg.domain.entity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkinModel(
    @SerialName("num") var skinNumber: Int? = null,
    @SerialName("name") var skinName: String? = null,
)