package com.dg.tvmaze.di

import com.dg.tvmaze.cache.EpisodeCache
import com.dg.tvmaze.repositories.EpisodeRepository
import com.dg.tvmaze.repositories.ShowRepository
import org.koin.dsl.module

val cachesModule = module {
    single { EpisodeCache() }
}