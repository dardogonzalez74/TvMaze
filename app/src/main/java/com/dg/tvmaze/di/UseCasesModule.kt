package com.dg.tvmaze.di

import com.dg.tvmaze.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    factory { RetrieveEpisodesUseCase(episodeRepository = get()) }
    factory { RetrieveShowsPagedUseCase(showRepository = get(), retrieveFavoritesUseCase = get()) }
    factory { RetrieveShowsBySearchUseCase(showRepository = get()) }
    factory { RetrieveFavoritesUseCase(showRepository = get()) }
    factory { UpdateFavoritesUseCase(showRepository = get()) }
}