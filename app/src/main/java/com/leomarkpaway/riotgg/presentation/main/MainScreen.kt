package com.leomarkpaway.riotgg.presentation.main

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leomarkpaway.riotgg.common.extension.coloredShadow
import com.leomarkpaway.riotgg.common.util.navigator.NavigationAction
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.common.util.navigator.ObserveAsEvents
import com.leomarkpaway.riotgg.presentation.main.component.NavigationHost
import com.leomarkpaway.riotgg.presentation.main.component.drawer.Drawer
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.orbitmvi.orbit.compose.collectAsState
import kotlin.math.roundToInt

@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    val state = viewModel.collectAsState().value
    val navController = rememberNavController()
    val navigator = koinInject<Navigator>()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when(action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.destination
            ) {
                action.navOptions(this)
            }
            NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 4.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (state.isDrawerOpen) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (state.isDrawerOpen) 0.9f else 1f,
        label = "Animated Scale"
    )

    BackHandler(enabled = state.isDrawerOpen) {
        viewModel.updateIsDrawerOpen(false)
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Drawer(
            selectedMainDrawerItem = state.selectedDrawerItem,
            onNavigationItemClick = {
                viewModel.updateSelectedDrawerItem(it)
                viewModel.updateIsDrawerOpen(false)
            },
            onCloseClick = { viewModel.updateIsDrawerOpen(false) }
        )
        MainContent(
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 50.dp
                ),
            state = state,
            onDrawerClick = { viewModel.updateIsDrawerOpen(isDrawerOpen = it) },
            navController = navController,
            navigator = navigator
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    state: MainState,
    onDrawerClick: (Boolean) -> Unit,
    navController: NavHostController,
    navigator: Navigator
) {
    Scaffold(
        modifier = modifier
            .clickable(enabled = state.isDrawerOpen) { onDrawerClick(false) },
        topBar = {
            TopAppBar(
                title = { Text(text = state.selectedDrawerItem.title) },
                navigationIcon = {
                    IconButton(onClick = { onDrawerClick(!state.isDrawerOpen) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                }
            )
        }
    ) { padding ->
        NavigationHost(
            padding = padding,
            navController = navController,
            navigator = navigator
        )
    }
}