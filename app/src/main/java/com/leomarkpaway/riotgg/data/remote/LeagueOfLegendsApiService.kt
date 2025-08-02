package com.leomarkpaway.riotgg.data.remote

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.ChampionEntity
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient

class LeagueOfLegendsApiService(private val httpClient: HttpClient) {

    suspend fun getAllChampions(): ApiResponse<ChampionEntity> {
        return httpClient.getApiResponse("cdn/15.4.1/data/en_US/champion.json")
    }

    suspend fun getChampionByName(name: String): ApiResponse<ChampionEntity> {
        return httpClient.getApiResponse("cdn/15.4.1/data/en_US/champion/$name.json")
    }

    companion object {
        private const val VERSION = "15.4.1"
        const val BASE_URL = "https://ddragon.leagueoflegends.com/cdn"
        const val CHAMPION_IMAGE_CARD_URL = "$BASE_URL/img/champion/loading/"
        const val CHAMPION_BACKGROUND_URL = "$BASE_URL/img/champion/splash/"
        const val CHAMPION_SQUARE_URL = "$BASE_URL/${VERSION}/img/champion/"
        const val CHAMPION_PASSIVE_URL = "$BASE_URL/${VERSION}/img/passive/"
        const val CHAMPION_ABILITY_URL = "$BASE_URL/${VERSION}/img/spell/"
    }

}