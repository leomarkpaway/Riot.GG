package com.leomarkpaway.riotgg.domain.entity.league_of_legends.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoModel(
  @SerialName("attack") var attack: Int? = null,
  @SerialName("defense") var defense: Int? = null,
  @SerialName("magic") var magic: Int? = null,
  @SerialName("difficulty") var difficulty: Int? = null
)