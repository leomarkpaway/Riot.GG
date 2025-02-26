package com.leomarkpaway.riotgg.di

import com.leomarkpaway.riotgg.data.repository.RepositoryImpl
import com.leomarkpaway.riotgg.domain.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(riotApiService = get())
    }
}