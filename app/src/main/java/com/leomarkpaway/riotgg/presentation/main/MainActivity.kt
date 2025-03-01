package com.leomarkpaway.riotgg.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsScreen
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsViewModel
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListScreen
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListSideEffect
import com.leomarkpaway.riotgg.presentation.champion_list.ChampionListViewModel
import com.leomarkpaway.riotgg.common.util.navigator.Destination
import com.leomarkpaway.riotgg.common.util.navigator.NavigationAction
import com.leomarkpaway.riotgg.common.util.navigator.Navigator
import com.leomarkpaway.riotgg.common.util.navigator.ObserveAsEvents
import com.leomarkpaway.riotgg.ui.theme.RiotGGTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RiotGGTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RiotGGTheme {
        Greeting("Android")
    }
}