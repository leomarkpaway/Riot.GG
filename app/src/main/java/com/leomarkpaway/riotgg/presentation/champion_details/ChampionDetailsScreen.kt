package com.leomarkpaway.riotgg.presentation.champion_details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.CHAMPION_BACKGROUND_URL
import com.leomarkpaway.riotgg.presentation.champion_details.composable.ChampionHeader
import com.leomarkpaway.riotgg.presentation.champion_details.composable.ChampionLore
import com.leomarkpaway.riotgg.presentation.champion_details.composable.ChampionPassive
import com.leomarkpaway.riotgg.presentation.champion_details.composable.ChampionSpell

@Composable
fun ChampionDetailsScreen(state: ChampionDetailsState) {
    state.champion?.let { champion ->
        Scaffold { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding
            ) {
                item {
                    AsyncImage(
                        model = "$CHAMPION_BACKGROUND_URL${champion.id}_0.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    ChampionHeader(
                        champion = champion,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                    )

                    ChampionLore(
                        lore = champion.lore ?: "",
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 6.dp
                        )
                    )

                    champion.passive?.let { passive ->
                        ChampionPassive(
                            passive = passive,
                            modifier = Modifier.padding(
                                horizontal = 6.dp,
                                vertical = 10.dp
                            )
                        )
                    }

                    champion.spells.forEach { spell ->
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
    }
}