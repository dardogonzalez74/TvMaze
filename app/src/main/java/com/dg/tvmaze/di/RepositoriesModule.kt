package com.dg.tvmaze.di

import com.dg.tvmaze.repositories.EpisodeRepository
import com.dg.tvmaze.repositories.IEpisodeRepository
import com.dg.tvmaze.repositories.ShowRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { ShowRepository(showDao = get(), showEndpoint = get(), searchEndpoint = get()) }
    single<IEpisodeRepository> { EpisodeRepository(episodeEndpoint = get(), episodeCache = get()) }
}