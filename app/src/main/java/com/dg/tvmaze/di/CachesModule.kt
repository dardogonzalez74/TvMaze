package com.dg.tvmaze.di

import com.dg.tvmaze.cache.EpisodeCache
import com.dg.tvmaze.cache.IEpisodeCache
import org.koin.dsl.module

val cachesModule = module {
    single<IEpisodeCache> { EpisodeCache() }
}