package com.leomarkpaway.riotgg.domain.usecase.league_of_legends

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.ChampionModel
import com.leomarkpaway.riotgg.data.remote.league_of_legends.toChampionList
import com.leomarkpaway.riotgg.domain.repository.LeagueOfLegendsRepository
import com.skydoves.sandwich.onSuccess

class FetchChampionListUsaCase(private val leagueOfLegendsRepository: LeagueOfLegendsRepository) {
    suspend operator fun invoke() : List<ChampionModel> {
        var championList: List<ChampionModel> = emptyList()
        leagueOfLegendsRepository.getAllChampions().onSuccess {
            championList = data.champion.toChampionList()
        }
        return championList
    }
}