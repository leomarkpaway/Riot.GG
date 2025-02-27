package com.leomarkpaway.riotgg.common.extension

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
inline fun <reified VM : androidx.lifecycle.ViewModel> getComposeViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): VM {
    return koinViewModel(qualifier = qualifier, parameters = parameters)
}