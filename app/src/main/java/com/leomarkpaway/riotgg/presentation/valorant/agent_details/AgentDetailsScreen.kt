package com.leomarkpaway.riotgg.presentation.valorant.agent_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import timber.log.Timber

@Composable
fun AgentDetailsScreen(agentUuid: String) {
    val viewModel = koinViewModel<AgentDetailsViewModel>()
    viewModel.fetchAgentDetails(agentUuid)
    val state = viewModel.collectAsState()
    Timber.d("${state.value}")
    if (state.value.isOnLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator(modifier = Modifier.padding(24.dp), color = Color.Black) }
    } else {
        Text("${state.value.agent?.displayName}")
        Timber.d("${state.value.agent?.displayName}")
    }
}