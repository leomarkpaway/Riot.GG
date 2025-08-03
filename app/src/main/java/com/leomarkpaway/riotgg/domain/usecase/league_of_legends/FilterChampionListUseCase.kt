package com.leomarkpaway.riotgg.domain.usecase.league_of_legends

import com.leomarkpaway.riotgg.domain.entity.league_of_legends.ChampionModel

class FilterChampionListUseCase {
    suspend operator fun invoke(championName: String, championList: List<ChampionModel>) : List<ChampionModel> {
        return championList.filter { it.name?.contains(championName, ignoreCase = true) == true }
    }
}