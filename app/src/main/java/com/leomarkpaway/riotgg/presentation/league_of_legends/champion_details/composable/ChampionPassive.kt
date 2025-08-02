package com.leomarkpaway.riotgg.presentation.league_of_legends.champion_details.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.leomarkpaway.riotgg.data.remote.LeagueOfLegendsApiService.Companion.CHAMPION_PASSIVE_URL
import com.leomarkpaway.riotgg.domain.entity.league_of_legends.model.PassiveModel

@Composable
fun ChampionPassive(passive: PassiveModel, modifier: Modifier = Modifier) {
    ListItem(
        headlineContent = {
            Text(text = passive.name ?: "")
        },
        supportingContent = {
            Text(text = passive.description ?: "")
        },
        leadingContent = {
            AsyncImage(
                model ="$CHAMPION_PASSIVE_URL${passive.image?.full}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        },
        modifier = modifier
    )
}