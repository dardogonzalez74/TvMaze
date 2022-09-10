package com.dg.tvmaze.di

import com.dg.tvmaze.usecases.RetrieveEpisodesUseCase
import com.dg.tvmaze.usecases.RetrieveShowsBySearchUseCase
import com.dg.tvmaze.usecases.RetrieveShowsPagedUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { RetrieveEpisodesUseCase(episodeRepository = get()) }
    factory { RetrieveShowsPagedUseCase(showRepository = get()) }
    factory { RetrieveShowsBySearchUseCase(showRepository = get()) }
}