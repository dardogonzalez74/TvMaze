package com.dg.tvmaze.di

import com.dg.tvmaze.ui.main.MainViewModel
import com.dg.tvmaze.ui.shows.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ShowDetailsViewModel(retrieveEpisodesUseCase = get()) }

    viewModel {
        MainViewModel(
            retrieveFavoritesUC = get(),
            updateFavoritesUC = get(),
            retrieveShowsPagedUC = get(),
            retrieveShowsBySearchUC = get()
        )
    }


}