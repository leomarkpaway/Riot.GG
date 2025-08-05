package com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leomarkpaway.riotgg.common.composable.SearchBar
import com.leomarkpaway.riotgg.presentation.league_of_legends.champion_list.composable.ChampionCard

@Composable
fun ChampionListScreen(
    state: ChampionListState,
    onSearchTextChange: (String) -> Unit,
    onClickItem: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar(
            searchValue = state.searchText,
            onSearchValueChange = onSearchTextChange,
            placeholder = "Search for champs"
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
                items(state.filteredChampions.ifEmpty { state.champions }) { champion ->
                    ChampionCard(
                        champion = champion,
                        modifier = Modifier
                            .animateItem()
                            .clickable { champion.id?.let(onClickItem) }
                    )
                }
            }
        }
    }
}