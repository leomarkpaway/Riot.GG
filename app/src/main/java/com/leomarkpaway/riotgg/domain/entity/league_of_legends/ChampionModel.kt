package com.leomarkpaway.riotgg.domain.entity.league_of_legends

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionModel(
    @SerialName("id")
    val id: String? = "",
    @SerialName("image")
    val image: ImageModel? = ImageModel(),
    @SerialName("key")
    val key: String? = "",
    @SerialName("lore")
    val lore: String? = "",
    @SerialName("blurb")
    val blurb: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("passive")
    val passive: PassiveModel? = PassiveModel(),
    @SerialName("spells")
    val spells: List<AbilityModel> = listOf(),
    @SerialName("tags")
    val tags: List<String> = listOf(),
    @SerialName("title")
    val title: String? = "",
    @SerialName("info")
    var info: InfoModel? = InfoModel(),
    @SerialName("stats")
    var stats: Stats? = Stats(),
    @SerialName("skins")
    val skins: ArrayList<SkinModel> = arrayListOf(),
    @SerialName("allytips")
    var allyTips: ArrayList<String> = arrayListOf(),
    @SerialName("enemytips")
    var enemyTips: ArrayList<String> = arrayListOf(),
)