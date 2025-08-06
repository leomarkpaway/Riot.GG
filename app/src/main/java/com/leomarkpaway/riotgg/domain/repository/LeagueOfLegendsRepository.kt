package com.leomarkpaway.riotgg.domain.repository

import com.leomarkpaway.riotgg.data.remote.league_of_legends.ChampionResponse
import com.skydoves.sandwich.ApiResponse

interface LeagueOfLegendsRepository {
    suspend fun getAllChampions(): ApiResponse<ChampionResponse>
    suspend fun getChampionByName(name: String): ApiResponse<ChampionResponse>
}