package com.leomarkpaway.riotgg.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.leomarkpaway.riotgg.R
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsScreen
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListScreen
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListSideEffect
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber

@Composable
fun NavigationHost(padding: PaddingValues, navController: NavHostController, navigator: Navigator) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(padding)) {
        NavHost(
            navController = navController,
            startDestination = navigator.startDestination,
        ) {
            composable<Destination.LeagueOfLegends> {
                val viewModel = koinViewModel<ChampionListViewModel>()
                val state = viewModel.collectAsState()

                viewModel.collectSideEffect {
                    when(it) {
                        is ChampionListSideEffect.OnError -> {
                            Timber.e("error ${it.message}")
                        }
                    }
                }

                ChampionListScreen(
                    state = state.value,
                    onSearchTextChange = viewModel::onSearchTextChange,
                    onClickItem = viewModel::onClickItem
                )
            }
            composable<Destination.ChampionDetails> {
                val args = it.toRoute<Destination.ChampionDetails>()
                val viewModel = koinViewModel<ChampionDetailsViewModel>()
                viewModel.fetchChampionDetails(args.championName)
                val state = viewModel.collectAsState().value
                ChampionDetailsScreen(state = state)
            }
            composable<Destination.TeamfightTactics> {
                ComingSoonScreen()
            }
            composable<Destination.Valorant> {
                ComingSoonScreen()
            }
            composable<Destination.Settings> {
                ComingSoonScreen()
            }
        }
    }
}

@Composable
fun ComingSoonScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(140.dp))
        Image(
            modifier = Modifier.size(200.dp).alpha(0.5f),
            painter = painterResource(id = R.drawable.coming_soon),
            alignment = Alignment.Center,
            contentDescription = "coming soon image"
        )
    }
}