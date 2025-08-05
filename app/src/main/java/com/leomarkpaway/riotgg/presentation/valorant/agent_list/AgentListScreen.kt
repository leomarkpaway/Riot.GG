package com.leomarkpaway.riotgg.presentation.valorant.agent_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leomarkpaway.riotgg.common.composable.SearchBar
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list.ChampionListState
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list.composable.ChampionCard
import com.leomarkpaway.riotgg.presentation.valorant.agent_list.composable.AgentCard
import timber.log.Timber

@Composable
fun AgentListScreen(
    state: AgentListState,
    onSearchTextChange: (String) -> Unit,
    onClickItem: (String) -> Unit
) {
    Timber.d("AgentListState ${state.agents}")
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar(
            searchValue = state.searchText,
            onSearchValueChange = onSearchTextChange,
            placeholder = "Search for agents"
        )
        if (state.isOnLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(modifier = Modifier.padding(24.dp), color = Color.Black) }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.filteredAgents.ifEmpty { state.agents }) { agent ->
                    AgentCard(
                        agent = agent,
                        modifier = Modifier
                            .animateItem()
                            .clickable { agent.uuid.let(onClickItem) }
                    )
                }
            }
        }
    }
}