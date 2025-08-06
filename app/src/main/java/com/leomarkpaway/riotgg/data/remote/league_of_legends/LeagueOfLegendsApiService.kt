package com.leomarkpaway.riotgg.data.remote.league_of_legends

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient

class LeagueOfLegendsApiService(private val httpClient: HttpClient) {

    suspend fun getAllChampions(): ApiResponse<ChampionResponse> {
        return httpClient.getApiResponse("cdn/15.4.1/data/en_US/champion.json")
    }

    suspend fun getChampionByName(name: String): ApiResponse<ChampionResponse> {
        return httpClient.getApiResponse("cdn/15.4.1/data/en_US/champion/$name.json")
    }

    companion object {
        private const val VERSION = "15.4.1"
        const val LOL_BASE_URL = "https://ddragon.leagueoflegends.com/cdn"
        const val CHAMPION_IMAGE_CARD_URL = "$LOL_BASE_URL/img/champion/loading/"
        const val CHAMPION_BACKGROUND_URL = "$LOL_BASE_URL/img/champion/splash/"
        const val CHAMPION_SQUARE_URL = "$LOL_BASE_URL/$VERSION/img/champion/"
        const val CHAMPION_PASSIVE_URL = "$LOL_BASE_URL/$VERSION/img/passive/"
        const val CHAMPION_ABILITY_URL = "$LOL_BASE_URL/$VERSION/img/spell/"
    }

}