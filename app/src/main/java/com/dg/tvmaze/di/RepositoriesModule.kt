package com.dg.tvmaze.di

import com.dg.tvmaze.repositories.EpisodeRepository
import com.dg.tvmaze.repositories.ShowRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { ShowRepository(showEndpoint = get(), searchEndpoint = get()) }
    single { EpisodeRepository(episodeEndpoint = get(), episodeCache = get()) }
}