package com.leomarkpaway.riotgg.presentation.champion_details.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.CHAMPION_SQUARE_URL
import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel

@Composable
fun ChampionHeader(
    champion: ChampionModel,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = champion.name ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        supportingContent = {
            Text(
                text = champion.tags.firstOrNull() ?: ""
            )
        },
        leadingContent = {
            AsyncImage(
                model = "$CHAMPION_SQUARE_URL${champion.name}.png",
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        },
        trailingContent = {
            Text(
                text = champion.title ?: "",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}
