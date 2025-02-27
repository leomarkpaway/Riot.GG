package com.leomarkpaway.riotgg.domain.usecase

import com.leomarkpaway.riotgg.domain.entity.model.ChampionModel
import com.leomarkpaway.riotgg.domain.entity.toChampionList
import com.leomarkpaway.riotgg.domain.repository.Repository
import com.skydoves.sandwich.onSuccess

class FetchChampionListUsaCase(private val repository: Repository) {
    suspend operator fun invoke() : List<ChampionModel> {
        var championList: List<ChampionModel> = emptyList()
        repository.getAllChampions().onSuccess {
            championList = data.champion.toChampionList()
        }
        return championList
    }
}