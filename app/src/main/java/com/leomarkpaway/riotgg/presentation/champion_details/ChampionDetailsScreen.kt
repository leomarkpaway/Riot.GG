package com.leomarkpaway.riotgg.presentation.champion_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.CHAMPION_BACKGROUND_URL
import com.leomarkpaway.riotgg.data.remote.RiotApiService.Companion.CHAMPION_SQUARE_URL
import com.leomarkpaway.riotgg.presentation.champion_details.section_tabs.AbilitiesContent
import com.leomarkpaway.riotgg.presentation.champion_details.section_tabs.GeneralContent
import com.leomarkpaway.riotgg.presentation.champion_details.section_tabs.SkinsContent
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import java.util.Locale

@Composable
fun ChampionDetailsScreen(championName: String) {
    val viewModel = koinViewModel<ChampionDetailsViewModel>()
    viewModel.fetchChampionDetails(championName)
    val state = viewModel.collectAsState()
    if (state.value.isOnLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator(modifier = Modifier.padding(24.dp), color = Color.Black) }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Header(state.value)
            SectionTabs(state.value)
        }
    }
}

@Composable
fun Header(state: ChampionDetailsState) {
    state.champion?.let { champion ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = "$CHAMPION_BACKGROUND_URL${champion.id}_0.jpg",
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
            ) {
                Row {
                    AsyncImage(
                        model = "$CHAMPION_SQUARE_URL${champion.name}.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = champion.name ?: "",
                            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = champion.tags.firstOrNull() ?: "",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    val title = champion.title?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                        else it.toString()
                    } ?: ""
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
fun SectionTabs(state: ChampionDetailsState) {
    val tabTitles = listOf("General", "Abilities", "Skins")
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            Modifier.background(Color.White),
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(3.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> GeneralContent(state)
            1 -> AbilitiesContent(state)
            2 -> SkinsContent(state)
        }
    }
}