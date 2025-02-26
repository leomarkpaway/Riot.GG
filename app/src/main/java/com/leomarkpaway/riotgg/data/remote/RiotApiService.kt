package com.leomarkpaway.riotgg.data.remote

import com.leomarkpaway.riotgg.domain.entity.ChampionEntity
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient

class RiotApiService(private val httpClient: HttpClient) {

    suspend fun getAllChampions(): ApiResponse<ChampionEntity> {
        return httpClient.getApiResponse("cdn/15.4.1/data/en_US/champion.json")
    }

    suspend fun getChampionByName(name: String): ApiResponse<ChampionEntity> {
        return httpClient.getApiResponse("cdn/15.4.1/data/en_US/champion/$name.json")
    }

    companion object {
        const val BASE_URL = "https://ddragon.leagueoflegends.com/cdn"
        const val CHAMPION_IMAGE_CARD_URL = "$BASE_URL/img/champion/loading/"
    }

}