package com.leomarkpaway.riotgg.data.repository

import com.leomarkpaway.riotgg.data.remote.league_of_legends.LeagueOfLegendsApiService
import com.leomarkpaway.riotgg.domain.repository.LeagueOfLegendsRepository

class LeagueOfLegendsRepositoryImpl(
    private val leagueOfLegendsApiService: LeagueOfLegendsApiService
) : LeagueOfLegendsRepository {
    override suspend fun getAllChampions() = leagueOfLegendsApiService.getAllChampions()
    override suspend fun getChampionByName(name: String) = leagueOfLegendsApiService.getChampionByName(name)
}