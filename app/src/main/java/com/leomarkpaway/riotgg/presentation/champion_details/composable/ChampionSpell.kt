package com.leomarkpaway.riotgg.presentation.champion_details.composable

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
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.CHAMPION_ABILITY_URL
import com.leomarkpaway.riotgg.domain.entity.model.SpellModel

@Composable
fun ChampionSpell(spell: SpellModel, modifier: Modifier = Modifier) {
    ListItem(
        headlineContent = {
            Text(text = spell.name ?: "")
        },
        supportingContent = {
            Text(text = spell.description ?: "")
        },
        leadingContent = {
            AsyncImage(
                model = "$CHAMPION_ABILITY_URL${spell.id}.png",
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