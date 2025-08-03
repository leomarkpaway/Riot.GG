package com.leomarkpaway.riotgg.domain.entity.league_of_legends

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassiveModel(
    @SerialName("description")
    val description: String? = "",
    @SerialName("image")
    val image: ImageModel? = ImageModel(),
    @SerialName("name")
    val name: String? = ""
)