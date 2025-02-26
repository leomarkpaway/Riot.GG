package com.leomarkpaway.riotgg.data.repository

import com.leomarkpaway.riotgg.data.remote.RiotApiService
import com.leomarkpaway.riotgg.domain.repository.Repository

class RepositoryImpl(private val riotApiService: RiotApiService) : Repository {
    override suspend fun getAllChampions() = riotApiService.getAllChampions()
    override suspend fun getChampionByName(name: String) = riotApiService.getChampionByName(name)
}