package com.leomarkpaway.riotgg.presentation.champion_details.section_tabs

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leomarkpaway.riotgg.domain.entity.model.InfoModel
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsState

@Composable
fun GeneralContent(
    state: ChampionDetailsState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item { ChampionLore(championLore = state.champion?.lore ?: "") }
        item { GeneralStatsCard(state.generalStats) }
        item { BasicAttributesCard(state.champion?.info ?: InfoModel()) }
    }
}

@Composable
fun ChampionLore(championLore: String) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable { isExpanded = !isExpanded }
    ) {
        Text(
            text = "Champion Lore",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = championLore,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = if (isExpanded) Int.MAX_VALUE else 5,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 22.sp,
            modifier = Modifier.animateContentSize()
        )

        Text(
            text = if (isExpanded) "Show less" else "Show more",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun GeneralStatsCard(statValues: List<String> = emptyList()) {
    val statLabels = listOf(
        "Health",
        "Health Regen",
        "Mana",
        "Mana Regen",
        "Range",
        "Atk Damage",
        "Atk Speed",
        "Armor",
        "Magic Res",
        "Movement Speed"
    )
    val stats = statLabels.zip(statValues)
    val skipLabels = "Movement Speed"
    val filteredStats = stats.filter { (label, _) -> label !in skipLabels }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Stats",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            val rows = filteredStats.chunked(3)
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { (label, value) ->
                        StatItem(label, value, Modifier.weight(1f))
                    }
                    repeat(3 - row.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val lastStat = stats.lastOrNull()
                val (label, value) = lastStat ?: ("Movement Speed" to "N/A")
                StatItem(label, value)
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
fun BasicAttributesCard(attributesValue: InfoModel) {
    val attributesListValue = listOf(
        attributesValue.attack ?: 0,
        attributesValue.defense ?: 0,
        attributesValue.magic ?: 0,
        attributesValue.difficulty ?: 0
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            attributesListValue.forEachIndexed { index, value ->
                AttributeBar(index, value)
            }
        }
    }
}

@Composable
fun AttributeBar(index: Int, value: Int) {
    val normalizedValue = value.coerceIn(0, 100) / 10f
    val attributes = listOf(
        Pair("Attack", Color(0xFFFF4C4C)),
        Pair("Defense", Color(0xFF4CAF50)),
        Pair("Magic", Color(0xFF42A5F5)),
        Pair("Difficulty", Color(0xFFAB47BC))
    )
    val (label, color) = attributes.getOrNull(index) ?: ("Unknown" to Color.Gray)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier.width(80.dp)
        )
        LinearProgressIndicator(
            progress = normalizedValue,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = Color.DarkGray
        )
    }
}