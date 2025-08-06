package com.leomarkpaway.riotgg.data.remote.league_of_legends

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.ChampionModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionResponse(
    @SerialName("data")
    val champion: Map<String, ChampionModel> = emptyMap(),
    @SerialName("format")
    val format: String? = "",
    @SerialName("type")
    val type: String? = "",
    @SerialName("version")
    val version: String? = ""
)

fun Map<String, ChampionModel>.toChampionList(): List<ChampionModel> =
    this.values.toList()
