package com.leomarkpaway.riotgg.presentation.valorant.agent_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.domain.usecase.valorant.FetchAgentDetailsUseCase
import com.leomarkpaway.riotgg.domain.usecase.valorant.FetchAgentListUseCase
import com.leomarkpaway.riotgg.domain.usecase.valorant.FilterAgentListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class AgentListViewModel(
    private val navigator: Navigator,
    private val fetchAgentListUseCase: FetchAgentListUseCase,
    private val filterAgentListUseCase: FilterAgentListUseCase
) : ContainerHost<AgentListState, AgentListSideEffect>, ViewModel() {
    override val container = container<AgentListState, AgentListSideEffect>(AgentListState())

    init {
        intent {
            viewModelScope.launch {
                val agentList = fetchAgentListUseCase.invoke()
                reduce { state.copy(agents = agentList) }
                delay(500)
                reduce { state.copy(isOnLoading = false) }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        intent {
            reduce { state.copy(searchText = text) }
            viewModelScope.launch(Dispatchers.IO) {
                val filteredAgents = filterAgentListUseCase.invoke(text, state.agents)
                reduce { state.copy(filteredAgents = filteredAgents) }
            }
        }
    }

    fun onClickItem(agentUuid: String) {
        Timber.d("agentUuid $agentUuid")
        viewModelScope.launch {
            //TODO navigate to agent details screen
        }
    }

}