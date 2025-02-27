package com.leomarkpaway.riotgg.domain.usecase

import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel

class FilterChampionListUseCase {
    suspend operator fun invoke(championName: String, championList: List<ChampionModel>) : List<ChampionModel> {
        return championList.filter { it.name?.contains(championName, ignoreCase = true) == true }
    }
}