package com.leomarkpaway.riotgg.domain.repository

import com.leomarkpaway.riotgg.domain.entity.ChampionEntity
import com.skydoves.sandwich.ApiResponse

interface Repository {
    suspend fun getAllChampions(): ApiResponse<ChampionEntity>
    suspend fun getChampionByName(name: String): ApiResponse<ChampionEntity>
}