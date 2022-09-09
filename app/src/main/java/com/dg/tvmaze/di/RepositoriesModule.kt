package com.dg.tvmaze.di

import com.dg.tvmaze.repositories.EpisodeRepository
import com.dg.tvmaze.repositories.ShowRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { ShowRepository(showsEndpoint = get()) }
    single { EpisodeRepository(episodesEndpoint = get()) }
}