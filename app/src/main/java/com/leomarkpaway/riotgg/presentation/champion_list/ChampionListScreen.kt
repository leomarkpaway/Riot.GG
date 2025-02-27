package com.leomarkpaway.riotgg.presentation.champion_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leomarkpaway.riotgg.common.extension.getComposeViewModel
import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel
import com.leomarkpaway.riotgg.presentation.champion_list.composable.ChampionCard
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber

@Composable
fun ChampionListScreen() {
    var championList = remember { mutableStateListOf<ChampionModel>() }
    val viewModel = getComposeViewModel<ChampionListViewModel>()
    val state = viewModel.collectAsState()

    viewModel.collectSideEffect {
        when(it) {
            is ChampionListSideEffect.OnError -> {
                Timber.e("error: ${it.message}")
            }
            is ChampionListSideEffect.OnSearch -> {
                championList = it.champions.toMutableStateList()
                Timber.d("SearchResult: $championList")
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            OutlinedTextField(
                value = state.value.searchText,
                onValueChange = viewModel::onSearchTextChange,
                placeholder = { Text(text = "Search for champs") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
            if (state.value.isOnLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator(modifier = Modifier.padding(24.dp), color = Color.Black) }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(championList.ifEmpty { state.value.champions } ) { champion ->
                        ChampionCard(
                            champion = champion,
                            modifier = Modifier
                                .animateItem()
                                .clickable { champion.name?.let(viewModel::onClickItem) }
                        )
                    }
                }
            }
        }
    }
}