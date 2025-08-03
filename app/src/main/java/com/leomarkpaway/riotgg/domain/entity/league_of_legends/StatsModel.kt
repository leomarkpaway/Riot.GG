package com.leomarkpaway.riotgg.domain.entity.league_of_legends

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
  @SerialName("hp")
  var hp: Int? = null,
  @SerialName("hpperlevel")
  var hpPerLevel: Int? = null,
  @SerialName("mp")
  var mp: Int? = null,
  @SerialName("mpperlevel")
  var mpPerLevel: Double? = null,
  @SerialName("movespeed")
  var moveSpeed: Int? = null,
  @SerialName("armor")
  var armor: Int? = null,
  @SerialName("armorperlevel")
  var armorPerLevel: Double? = null,
  @SerialName("spellblock")
  var spellBlock: Int? = null,
  @SerialName("spellblockperlevel")
  var spellBlockPerLevel: Double? = null,
  @SerialName("attackrange")
  var attackRange: Int? = null,
  @SerialName("hpregen") var
  hpRegen: Double? = null,
  @SerialName("hpregenperlevel")
  var hpRegenPerLevel: Double? = null,
  @SerialName("mpregen")
  var mpRegen: Double? = null,
  @SerialName("mpregenperlevel")
  var mpRegenPerLevel: Double? = null,
  @SerialName("crit")
  var crit: Int? = null,
  @SerialName("critperlevel")
  var critPerLevel: Int? = null,
  @SerialName("attackdamage")
  var attackDamage: Int? = null,
  @SerialName("attackdamageperlevel")
  var attackDamagePerLevel: Double? = null,
  @SerialName("attackspeedperlevel")
  var attackSpeedPerLevel: Double? = null,
  @SerialName("attackspeed")
  var attackSpeed: Double? = null
)