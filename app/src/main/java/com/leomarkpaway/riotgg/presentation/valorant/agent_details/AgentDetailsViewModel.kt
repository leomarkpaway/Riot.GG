package com.leomarkpaway.riotgg.presentation.valorant.agent_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.riotgg.domain.usecase.valorant.FetchAgentDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AgentDetailsViewModel(
    private val fetchAgentDetailsUseCase: FetchAgentDetailsUseCase
) : ContainerHost<AgentDetailsState, Nothing>,ViewModel() {
    override val container = container<AgentDetailsState, Nothing>(AgentDetailsState())

    fun fetchAgentDetails(agentUuid: String) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            val agentDetails = fetchAgentDetailsUseCase.invoke(agentUuid)
            if (agentDetails != null) {
                reduce { state.copy(agent = agentDetails) }
                delay(500)
                reduce { state.copy(isOnLoading = false) }
            }
        }
    }
}