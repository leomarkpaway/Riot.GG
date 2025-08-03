package com.leomarkpaway.riotgg.domain.repository

import com.leomarkpaway.riotgg.data.remote.league_of_legends.ChampionEntity
import com.skydoves.sandwich.ApiResponse

interface LeagueOfLegendsRepository {
    suspend fun getAllChampions(): ApiResponse<ChampionEntity>
    suspend fun getChampionByName(name: String): ApiResponse<ChampionEntity>
}