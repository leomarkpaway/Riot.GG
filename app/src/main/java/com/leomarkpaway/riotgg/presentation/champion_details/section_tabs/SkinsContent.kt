package com.leomarkpaway.riotgg.presentation.champion_details.section_tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leomarkpaway.riotgg.presentation.champion_details.ChampionDetailsState
import com.leomarkpaway.riotgg.presentation.champion_details.composable.ChampionSkinCard
import com.leomarkpaway.riotgg.presentation.champion_details.composable.PreviewDialog

@Composable
fun SkinsContent(state: ChampionDetailsState) {
    var isShowPreviewDialog by remember { mutableStateOf(false) }
    var skinName by remember { mutableStateOf("") }
    var skinImageURL by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.skins) { skin ->
            ChampionSkinCard(
                championId = state.champion?.id ?: "",
                skin = skin,
                onClickItem = { imageURL ->
                    isShowPreviewDialog = true
                    skinName = skin.first
                    skinImageURL = imageURL
                }
            )
        }
    }

    if (isShowPreviewDialog && skinName.isNotBlank() && skinImageURL.isNotBlank()) {
        PreviewDialog(
            skinName = skinName,
            skinImageURl = skinImageURL,
            onDismissRequest = { isShowPreviewDialog = false },
        )
    }
}