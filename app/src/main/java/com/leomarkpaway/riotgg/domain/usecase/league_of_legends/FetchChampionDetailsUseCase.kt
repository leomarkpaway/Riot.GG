package com.leomarkpaway.riotgg.domain.usecase.league_of_legends

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.ChampionModel
import com.leomarkpaway.riotgg.domain.repository.LeagueOfLegendsRepository
import com.skydoves.sandwich.onSuccess

class FetchChampionDetailsUseCase(private val leagueOfLegendsRepository: LeagueOfLegendsRepository) {
    suspend operator fun invoke(championName: String) : ChampionModel? {
        var championDetails: ChampionModel? = ChampionModel()
        leagueOfLegendsRepository.getChampionByName(championName).onSuccess {
            championDetails = data.champion.values.firstOrNull()
        }
        return championDetails
    }
}