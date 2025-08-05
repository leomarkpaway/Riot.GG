package com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.section_tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.ChampionDetailsState
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.composable.ChampionPassive
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.composable.ChampionSpell

@Composable
fun AbilitiesContent(state: ChampionDetailsState) {
    LazyColumn {
        item {
            state.champion?.passive?.let { passive ->
                ChampionPassive(
                    passive = passive,
                    modifier = Modifier.padding(
                        horizontal = 6.dp,
                        vertical = 10.dp
                    )
                )
            }
        }
        item {
            state.champion?.spells?.forEach { spell ->
                ChampionSpell(
                    spell = spell,
                    modifier = Modifier.padding(
                        horizontal = 6.dp,
                        vertical = 10.dp
                    )
                )
            }
        }
    }
}