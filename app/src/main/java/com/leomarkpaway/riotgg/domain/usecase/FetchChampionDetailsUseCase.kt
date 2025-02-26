package com.leomarkpaway.riotgg.domain.usecase

import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel
import com.leomarkpaway.riotgg.domain.repository.Repository
import com.skydoves.sandwich.onSuccess

class FetchChampionDetailsUseCase(private val repository: Repository) {
    suspend operator fun invoke(championName: String) : ChampionModel? {
        var championDetails: ChampionModel? = ChampionModel()
        repository.getChampionByName(championName).onSuccess {
            championDetails = data.champion.values.firstOrNull()
        }
        return championDetails
    }
}