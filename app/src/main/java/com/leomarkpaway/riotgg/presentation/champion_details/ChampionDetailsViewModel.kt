package com.leomarkpaway.riotgg.presentation.champion_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel
import com.leomarkpaway.riotgg.domain.usecase.FetchChampionDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ChampionDetailsViewModel(
    private val fetchChampionDetailsUseCase: FetchChampionDetailsUseCase
) : ContainerHost<ChampionDetailsState, Nothing>,ViewModel() {
    override val container = container<ChampionDetailsState, Nothing>(ChampionDetailsState())

    fun fetchChampionDetails(championName: String) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            val championDetails = fetchChampionDetailsUseCase.invoke(championName)
            if (championDetails != null) {
                reduce { state.copy(champion = championDetails) }
                reduce { state.copy(generalStats = getGeneralStats(championDetails)) }
            }
        }
    }

    private fun getGeneralStats(champion: ChampionModel) : List<String> {
        val health = parseStatToString(champion.stats?.hp ?: 0, champion.stats?.hpPerLevel ?: 0)
        val healthRegen = parseStatToString(champion.stats?.hpRegen ?: 0.0, champion.stats?.hpRegenPerLevel ?: 0.0)
        val manaStat = parseStatToString(champion.stats?.mp ?: 0, champion.stats?.mpPerLevel ?: 0.0)
        val manaRegen = parseStatToString(champion.stats?.mpRegen ?: 0.0, champion.stats?.mpRegenPerLevel ?: 0.0)
        val rage = champion.stats?.attackRange.toString()
        val attackDamage = parseStatToString(champion.stats?.attackDamage ?: 0, champion.stats?.attackDamagePerLevel ?: 0.0)
        val attackSpeed = parseStatToString(champion.stats?.attackSpeed ?: 0.0, champion.stats?.attackSpeedPerLevel ?: 0.0)
        val armor = parseStatToString(champion.stats?.armor ?: 0, champion.stats?.armorPerLevel ?: 0.0)
        val magicResist = parseStatToString(champion.stats?.spellBlock ?: 0, champion.stats?.spellBlockPerLevel ?: 0.0)
        val movementSpeed = champion.stats?.moveSpeed.toString()
        val generalStats = listOf(health, healthRegen, manaStat, manaRegen, rage, attackDamage, attackSpeed, armor, magicResist, movementSpeed)
        return generalStats
    }

    private fun parseStatToString(initialValue: Int, perLevelValue: Int) : String {
        val maxValue = initialValue + (perLevelValue * 18)
        return if (initialValue == 0) "N/A" else "$initialValue - $maxValue"
    }

    private fun parseStatToString(initialValue: Double, perLevelValue: Double) : String {
        val maxValue = initialValue + (perLevelValue * 18)
        val formattedInitialValue = formatDouble(initialValue)
        val formattedMaxValue = formatDouble(maxValue)
        return if (initialValue == 0.0) "N/A" else "$formattedInitialValue - $formattedMaxValue"
    }

    private fun parseStatToString(initialValue: Int, perLevelValue: Double) : String {
        val maxValue = initialValue + (perLevelValue * 18)
        val formattedMaxValue = formatDouble(maxValue)
        return if (initialValue == 0) "N/A" else "$initialValue - $formattedMaxValue"
    }

    private fun formatDouble(value: Double): String {
        return if (value % 1.0 == 0.0) value.toInt().toString() else String.format("%.2f", value)
    }

}